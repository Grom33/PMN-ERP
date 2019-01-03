package ru.gromov.notification.domain.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gromov.notification.domain.User;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
public class ExternalServiceAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToMany(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<User> user;

}
