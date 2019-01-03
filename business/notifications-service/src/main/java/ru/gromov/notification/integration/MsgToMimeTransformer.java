package ru.gromov.notification.integration;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.gromov.notification.domain.message.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class MsgToMimeTransformer {
	@Autowired
	private JavaMailSender mailSender;

	public MimeMessage mailCreation(MailMessage msg) {

		MimeMessage mimeMessage;

		try {
			mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.toString());
			message.setTo(msg.getAdress());
			message.setSubject(msg.getMessage());
			message.setText(msg.getMessage(), false);
			message.setSentDate(new Date(System.currentTimeMillis()));
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return mimeMessage;
	}
}
