package ru.gromov.notification.service.sender.sms.providers.smsru;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.gromov.notification.domain.message.Sms;

class restUtil {
	static MultiValueMap<String, String> getParamsForMessage(Sms message, String api) {
		MultiValueMap<String, String> params = getParams(api);
		params.add("to", message.getPhoneNumber());
		params.add("msg", message.getMessage());
		return params;
	}

	static MultiValueMap<String, String> getParams(String api) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("json", "1");
		params.add("api_id", api);
		return params;
	}

	static HttpEntity<MultiValueMap<String, String>> getRequest(
			MultiValueMap<String, String> params) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return new HttpEntity<>(params, headers);
	}

}
