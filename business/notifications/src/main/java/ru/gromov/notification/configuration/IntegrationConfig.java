package ru.gromov.notification.configuration;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.scheduling.PollerMetadata;
//import org.springframework.messaging.Message;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.Message;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.integration.MsgToMimeTransformer;
import ru.gromov.notification.service.message.MailMessageService;
import ru.gromov.notification.service.message.SmsMessageService;
import ru.gromov.notification.service.sender.sms.SMSSender;

import java.util.Comparator;


@Configuration
@IntegrationComponentScan
public class IntegrationConfig {
	private final int MAIN_CHANNEL_CAPACITY = 1000;
	private final int SERVICE_CHANNEL_CAPACITY = 500;
	private final int POLLER_RATE = 500;

	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.port}")
	private int port;
	@Value("${mail.protocol}")
	private String protocol;
	@Value("${mail.user}")
	private String user;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.from}")
	private String from;
	@Value("${mail.encoding}")
	private String encoding;
	@Value("${mail.smtp.auth}")
	private boolean smtpAuth;
	@Value("${mail.smtp.starttls.enable}")
	private boolean smtpStartTlsEnable;
	@Value("${mail.debug}")
	private boolean debug;

	@Autowired
	private MsgToMimeTransformer toMimeTransformer;


	@Bean
	public JavaMailSenderImpl mailSender() {
		return new JavaMailSenderImpl();
	}

	@Bean(name = "toSend")
	public PollableChannel channelToSend() {
		return new QueueChannel(MAIN_CHANNEL_CAPACITY);
	}

	@Bean(name = "toMail")
	public MessageChannel channelToMail() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean(name = "toSmsService")
	public MessageChannel channelToMailService() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean(name = "toSMS")
	public PriorityChannel channelToSMS() {
		return new PriorityChannel(
				SERVICE_CHANNEL_CAPACITY,
				Comparator.comparing(left -> ((Sms) left.getPayload()).getPriority()));
	}

	@Bean(name = "toRepository")
	public PollableChannel channelToRep() {
		return new QueueChannel(SERVICE_CHANNEL_CAPACITY);
	}


	@Bean(name = "toStatistic")
	public PollableChannel channelToStatistic() {
		return new QueueChannel(SERVICE_CHANNEL_CAPACITY);
	}


	@Bean
	@Autowired
	public IntegrationFlow flowToSendMessage(@Qualifier("toSend") PollableChannel pollableChannel) {
		return IntegrationFlows
				.from(pollableChannel)
				.<Message, Class>route(
						(Message::getClass),
						mapping -> mapping
								.subFlowMapping(MailMessage.class, sf -> sf.channel("toMail"))
								.subFlowMapping(Sms.class, sf -> sf.channel("toSMS"))
				).get();
	}

	@Bean
	@Autowired
	public IntegrationFlow flowToSaveMessage(
			@Qualifier("toSmsService") MessageChannel messageChannel,
			SmsMessageService service) {
		return IntegrationFlows
				.from(messageChannel)
				.handle(message -> service.save((Sms) message.getPayload()))
				.get();
	}

	@Bean
	@Profile("prod")
	public IntegrationFlow flowMail() {
		return IntegrationFlows
				.from(this.channelToMail())
				.enrichHeaders(h -> h.header(MailHeaders.FROM, from))
				.transform(toMimeTransformer)
				.handle(
						Mail.outboundAdapter(host)
								.port(port)
								.protocol(protocol)
								.credentials(user, password)
								.defaultEncoding(encoding)
								.javaMailProperties(p -> {
									p.put("mail.smtp.auth", smtpAuth);
									p.put("mail.smtp.starttls.enable", smtpStartTlsEnable);
									p.put("mail.debug", debug);
								}),
						e -> e.id("sendMailEndpoint"))
				.get();
	}

	@Bean
	public IntegrationFlow flowMailtoStat() {
		return IntegrationFlows
				.from(this.channelToMail())
				.handle(message -> System.out.println("To statistic =====" + message))
				.get();
	}

	@Bean
	@Autowired
	@Profile("prod")
	public IntegrationFlow flowToSms(
			@Qualifier("toSMS") PollableChannel pollableChannel,
			SMSSender smsSender) {
		return IntegrationFlows
				.from(pollableChannel)
				.handle(message -> {
					smsSender.sendMessage((Sms) message.getPayload());
				})
				.get();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	@Primary
	public PollerMetadata poller() {
		return Pollers.fixedRate(POLLER_RATE).get();
	}


}
