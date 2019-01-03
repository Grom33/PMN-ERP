package ru.gromov.notification.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.gromov.notification.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
public class Message implements Comparable<Message>{
	public static final int START_SEQ = 100000;

	@Id
	@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
	private long id;

	@Column(name = "uuid")
	private UUID uuid;

	@Column(name = "status")
	private MessageStatus status;

	@Column(name = "message")
	private String message;

	@Column(name = "priority")
	private MessagePriority priority;

	@Column(name = "date_time_created", nullable = false)
	@NotNull
	private LocalDateTime dateTimeCreated;

	@Column(name = "date_time_completed")
	private LocalDateTime dateTimeCompleted;

	@Column(name = "date_time_to_send")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dateTimeToSend;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	@Override
	public int compareTo(Message that) {
		return (this.priority.compareTo(that.priority));
	}
}
