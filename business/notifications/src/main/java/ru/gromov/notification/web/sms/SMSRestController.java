package ru.gromov.notification.web.sms;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.domain.message.MessagePriority;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.dto.SmsDto;
import ru.gromov.notification.service.message.SmsMessageService;
import ru.gromov.notification.service.security.UserService;
import ru.gromov.notification.util.DtoUtil;

import java.util.List;
import java.util.UUID;

import static ru.gromov.notification.web.RestUtil.prepareMessage;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SMSRestController {
	@Autowired
	private SmsMessageService service;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/api/sms", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SmsDto> getAllUserMessage(Authentication authentication) {
		User loggedInUser = userService.findByUsername(authentication.getName());
		return DtoUtil.getSmsDto(loggedInUser.getSms());
	}

	@GetMapping(value = "/api/sms/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SmsDto> getAllMessage(Authentication authentication) {
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			log.info("user is admin");
			return DtoUtil.getSmsDto(service.getAll());
		}
		return null;
	}

	@PostMapping(value = "/api/sms", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addMessage(Authentication authentication, @RequestBody Sms sms) {
		sms.setPriority(MessagePriority.MEDIUM);
		return service.send(
				(Sms) prepareMessage(authentication, sms,
						userService.findByUsername(authentication.getName())))
				.getUuid().toString();
	}

	@DeleteMapping(value = "/api/sms/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SmsDto deleteMessage(@PathVariable UUID uuid) {
		return DtoUtil.getSmsDto(service.archive(uuid));
	}

	@GetMapping(value = "/api/sms/{uuid}/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public SmsDto checkMailStatus(@PathVariable UUID uuid) {
		return DtoUtil.getSmsDto(service.checkStatus(uuid));
	}

	@DeleteMapping(value = "/api/sms/{uuid}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
	public SmsDto archiveMessage(@PathVariable UUID uuid) {
		return DtoUtil.getSmsDto(service.archive(uuid));
	}

}
