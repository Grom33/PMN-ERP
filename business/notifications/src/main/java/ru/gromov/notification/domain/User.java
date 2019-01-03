package ru.gromov.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.domain.provider.EmailExternalServiceAccount;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "serv_user")
public class User {
	public static final int START_SEQ = 100000;

	@Id
	@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
	private long id;

	@ManyToMany(targetEntity = EmailExternalServiceAccount.class,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<EmailExternalServiceAccount> emailExternalServiceAccounts;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<MailMessage> mails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Sms> sms;

	@Column(name = "username")
	private String username;

	public User(String username) {
		this.username = username;
	}
}
