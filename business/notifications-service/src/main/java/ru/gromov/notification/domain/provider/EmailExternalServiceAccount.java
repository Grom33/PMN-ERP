package ru.gromov.notification.domain.provider;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@RequiredArgsConstructor
@ToString(callSuper = true)
@Table(name = "mail_account")
@EqualsAndHashCode(callSuper = true)
public class EmailExternalServiceAccount extends ExternalServiceAccount {
	private String host;
	private int port;
	private String username;
	private String password;

	private String mailTransportProtocol;
	private boolean mailSmtpAuth;
	private boolean mailSmtpStarttlsEnable;
	private boolean mailDebug;
}
