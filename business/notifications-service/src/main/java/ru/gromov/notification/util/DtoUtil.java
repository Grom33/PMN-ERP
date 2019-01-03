package ru.gromov.notification.util;

import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.dto.MailDTO;
import ru.gromov.notification.dto.SmsDto;

import java.util.List;
import java.util.stream.Collectors;

public class DtoUtil {

	public static MailDTO getMailDto(MailMessage mailMessage) {
		return new MailDTO(mailMessage.getUuid(),
				mailMessage.getDateTimeCreated(),
				mailMessage.getStatus(),
				mailMessage.getDateTimeCompleted());
	}

	public static List<MailDTO> getMailDto(List<MailMessage> mailMessageList) {
		if (mailMessageList != null) {
			return mailMessageList.stream()
					.map(DtoUtil::getMailDto)
					.collect(Collectors.toList());
		}
		return null;
	}

	public static SmsDto getSmsDto(Sms sms) {
		return new SmsDto(sms.getUuid(),
				sms.getDateTimeCreated(),
				sms.getResponseStatus(),
				sms.getDateTimeCompleted(),
				sms.getPhoneNumber());
	}

	public static List<SmsDto> getSmsDto(List<Sms> smsList) {
		if (smsList != null) {
			return smsList.stream()
					.map(DtoUtil::getSmsDto)
					.collect(Collectors.toList());
		}
		return null;
	}
}
