package ru.gromov.notification.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class MailMessage extends Message {
	@Column(name = "adress")
	private String adress;

	@Column(name = "subject")
	private String subject;
}
