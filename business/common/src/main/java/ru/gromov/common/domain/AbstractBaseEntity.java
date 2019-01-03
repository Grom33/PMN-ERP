package ru.gromov.common.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public abstract class AbstractBaseEntity implements HasId {
	public static final int START_SEQ = 10000;

	@Id
	@SequenceGenerator(name = "global_seq",
			sequenceName = "global_seq",
			allocationSize = 1,
			initialValue = START_SEQ)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
	//  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
	protected Long id;

	@Version
	@Column(name = "version", nullable = false)
	protected long versionUID;

	@Column(name = "active", nullable = false)
	protected Boolean active;

	@Column(name = "trash", nullable = false)
	protected Boolean trash;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	protected Long createdAt;

	@Column(name = "modified_date")
	@LastModifiedDate
	protected Long lastModified;


	//toDo https://www.baeldung.com/database-auditing-jpa
/*	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;*/


}
