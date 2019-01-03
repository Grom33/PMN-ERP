package ru.gromov.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.gromov.notification.domain.message.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MailDTO {

	private UUID uuid;
	private LocalDateTime dateTimeCreated;
	private MessageStatus status;
	private LocalDateTime dateTimeCompleted;

}
