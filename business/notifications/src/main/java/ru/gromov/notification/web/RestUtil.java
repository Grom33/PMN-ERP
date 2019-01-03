package ru.gromov.notification.web;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.security.core.Authentication;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.domain.message.Message;
import ru.gromov.notification.domain.message.MessageStatus;


import java.time.LocalDateTime;
import java.util.UUID;

public class RestUtil {
	public static Message prepareMessage(Authentication authentication,
	                                     Message message,
	                                     User loggedInUser) {
		message.setStatus(MessageStatus.CREATED);
		message.setUuid(UUID.randomUUID());
		message.setDateTimeCreated(LocalDateTime.now());
		message.setUser(loggedInUser);
		if (message.getDateTimeToSend() == null) message.setDateTimeToSend(LocalDateTime.now());
		return message;
	}
}
