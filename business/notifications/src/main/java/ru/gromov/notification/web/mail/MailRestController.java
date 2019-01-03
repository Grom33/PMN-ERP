package ru.gromov.notification.web.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.MessagePriority;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.dto.MailDTO;
import ru.gromov.notification.service.message.MailMessageService;
import ru.gromov.notification.service.security.UserService;
import ru.gromov.notification.util.DtoUtil;

import java.time.LocalDateTime;
import java.util.*;

import static ru.gromov.notification.web.RestUtil.prepareMessage;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailRestController {

	@Autowired
	private MailMessageService mailMessageService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/api/mails", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MailDTO> getAllUserMessage(Authentication authentication) {
		User loggedInUser = userService.findByUsername(authentication.getName());
		log.info("Logged user is {} " + loggedInUser);
		return DtoUtil.getMailDto(loggedInUser.getMails());
	}

	@GetMapping(value = "/api/mails/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MailDTO> getAllMessage(Authentication authentication) {
		log.info("Authorities: " + authentication.getAuthorities());
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			log.info("user is admin");
			return DtoUtil.getMailDto(mailMessageService.getAll());
		}
		return null;
	}

	@PostMapping(value = "/api/mails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addMessage(Authentication authentication, @RequestBody MailMessage mailMessage) {
		log.debug("POST request to add message, with message payload:{} ", mailMessage);
		mailMessage.setPriority(MessagePriority.MEDIUM);
		return mailMessageService.send(
				(MailMessage) prepareMessage(authentication, mailMessage,
						userService.findByUsername(authentication.getName())))
				.getUuid().toString();
	}

	@DeleteMapping(value = "/api/mails/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MailDTO deleteMessage(@PathVariable UUID uuid) {
		log.debug("DELETE request to delete message, with uuid:{} ", uuid);
		return DtoUtil.getMailDto(mailMessageService.archive(uuid));
	}

	@GetMapping(value = "/api/mails/{uuid}/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public MailDTO checkMailStatus(@PathVariable UUID uuid) {
		log.debug("GET request, with uuid:{} ", uuid);
		return DtoUtil.getMailDto(mailMessageService.getByUuiD(uuid));
	}

	@DeleteMapping(value = "/api/mails/{uuid}/archive", produces = MediaType.APPLICATION_JSON_VALUE)
	public MailDTO archiveMessage(@PathVariable UUID uuid) {
		log.debug("DELETE request to archive message, with uuid:{} ", uuid);
		return DtoUtil.getMailDto(mailMessageService.archive(uuid));
	}
}
