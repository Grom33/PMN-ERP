package ru.gromov.notification.service.sender.sms.providers.smsru;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.gromov.notification.domain.message.Message;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.service.sender.sms.SMSResponseStatus;
import ru.gromov.notification.service.sender.sms.SMSSender;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static ru.gromov.notification.service.sender.sms.providers.smsru.restUtil.*;

@Slf4j
@NoArgsConstructor
@Component("SMS.RU")
public class SMSSenderImpl implements SMSSender {
	@Autowired
	@Qualifier("toSmsService")
	private MessageChannel channelToService;
	@Value("${sms.providers.smsru.api_id}")
	private String API_ID;
	@Value("${sms.providers.smsru.url.send}")
	private String SEND_URL;
	@Value("${sms.providers.smsru.url.check}")
	private String CHECK_URL;
	@Value("${sms.providers.smsru.url.cost}")
	private String COST_URL;
	@Value("${sms.providers.smsru.url.balance}")
	private String BALANCE_URL;
	private final String TIME_ZONE_OFFSET = "+3";
	private final RestTemplate template = new RestTemplate();


	@Override
	public Message sendMessage(Sms message) {
		log.info("Begin to send message {}", message);
		ResponseEntity<String> response = getResponse(getParamsForMessage(message, API_ID), SEND_URL);
		channelToService.send(
				getChannelMessage(
						readSendedMessageAttribute(message, response)));
		return message;
	}

	@Override
	public BigDecimal checkMessageCost(Sms message) {
		log.info("Check message cost {}", message);
		ResponseEntity<String> response = getResponse(getParamsForMessage(message, API_ID), COST_URL);
		BigDecimal cost = getMessageCostAttribute(message.getPhoneNumber(), response);
		if (cost != null) return cost;
		return null;
	}


	@Override
	public Message checkMessageStatus(Sms message) {
		log.info("Check message status {}", message);
		MultiValueMap<String, String> params = getParams(API_ID);
		params.add("sms_id", message.getSmsId());
		ResponseEntity<String> response = getResponse(params, CHECK_URL);
		channelToService.send(getChannelMessage(getCheckedMessageAttribute(message, response)));
		if (message.getResponseStatus().code == 103) {
			message.setStatus(MessageStatus.SANDED);
			message.setDateTimeCompleted(message.getStatusTime());
		}
		return message;
	}

	@Override
	public BigDecimal checkBalance() {
		log.info("Check balance ...");
		ResponseEntity<String> response = getResponse(getParams(API_ID), BALANCE_URL);
		BigDecimal root = getBalanceAttribute(response);
		if (root != null) return root;
		return null;
	}

	private BigDecimal getBalanceAttribute(ResponseEntity<String> response) {
		try {
			JSONObject root = (JSONObject) new JSONParser().parse(response.getBody());
			return BigDecimal.valueOf((Double) root.get("balance"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Sms getCheckedMessageAttribute(Sms message, ResponseEntity<String> response) {
		try {
			JSONObject item = getJsonObject(message.getSmsId(), response);
			int statusCode = Integer.parseInt((String) item.get("status_code"));
			message.setResponseStatus(SMSResponseStatus.forValue(statusCode));
			message.setCost(
					BigDecimal.valueOf(
							Double.valueOf((String) item.get("cost"))));
			LocalDateTime statusTime = LocalDateTime.ofEpochSecond(
					((Long) item.get("status_time")),
					0, ZoneOffset.of(TIME_ZONE_OFFSET));
			message.setStatusTime(statusTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return message;
	}

	private Sms readSendedMessageAttribute(Sms message, ResponseEntity<String> response) {
		try {
			JSONObject item = getJsonObject(message.getPhoneNumber(), response);
			message.setResponseStatus(
					SMSResponseStatus.forValue(((Long) item.get("status_code")).intValue()));
			message.setSmsId((String) item.get("sms_id"));
			message.setStatusTime(LocalDateTime.now());
			message.setStatus(MessageStatus.IN_PROCESS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return message;
	}

	private BigDecimal getMessageCostAttribute(String phoneNumber, ResponseEntity<String> response) {
		try {
			JSONObject item = getJsonObject(phoneNumber, response);
			return BigDecimal.valueOf(Double.valueOf(String.valueOf(item.get("cost"))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private JSONObject getJsonObject(String field, ResponseEntity<String> response) throws ParseException {
		return (JSONObject) ((JSONObject) ((JSONObject)
				new JSONParser().parse(response.getBody()))
				.get("sms"))
				.get(field);
	}

	private org.springframework.messaging.Message<Sms> getChannelMessage(Sms sms) {
		return MessageBuilder.withPayload(sms).build();
	}

	private ResponseEntity<String> getResponse(MultiValueMap<String, String> params, String url) {
		return template.postForEntity(url, getRequest(params), String.class);
	}


}
