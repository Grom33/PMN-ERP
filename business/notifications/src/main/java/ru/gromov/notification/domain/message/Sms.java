package ru.gromov.notification.domain.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.gromov.notification.service.sender.sms.SMSResponseStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class Sms extends Message {

	@Column(name = "phone_number")
	private String phoneNumber;

	private SMSResponseStatus responseStatus;
	private BigDecimal cost;
	private String smsId;
	private LocalDateTime StatusTime;
}
