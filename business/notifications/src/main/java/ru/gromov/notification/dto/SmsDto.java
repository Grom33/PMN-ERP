package ru.gromov.notification.dto;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.gromov.notification.service.sender.sms.SMSResponseStatus;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
public class SmsDto {
	private UUID uuid;
	private LocalDateTime dateTimeCreated;
	private SMSResponseStatus status;
	private LocalDateTime dateTimeCompleted;
	private String phoneNumber;

}
