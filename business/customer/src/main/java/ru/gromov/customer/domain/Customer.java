package ru.gromov.customer.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import ru.gromov.common.domain.AbstractBaseEntity;
import ru.gromov.common.domain.Supervised;
import ru.gromov.common.domain.compliance.ComplianceStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
@Table(name = "customer")
@AllArgsConstructor
@ToString(callSuper = true)
public class Customer extends AbstractBaseEntity implements Supervised {

	@Column(name = "customer_uuid")
	private UUID customerUuid;

	@Enumerated(EnumType.STRING)
	@Column(name = "compliance_status", nullable = false)
	private ComplianceStatus complianceStatus;

	@Column(name = "customer_name", nullable = false)
	@NotBlank
	private String name;

	@Column(name = "middleName", nullable = false)
	@NotBlank
	private String middleName;

	@Column(name = "surname", nullable = false)
	@NotBlank
	private String surname;

	@Column(name = "maiden_name")
	private String MaidenName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	@NotNull
	private Gender gender;

	@Column(name = "birth_place", nullable = false)
	@NotBlank
	private String BirthPlace;

	@Column(name = "birthday", nullable = false)
	@NotNull
	private LocalDate Birthday;

	@Column(name = "inn")
	private String INN;

	@Column(name = "snils", nullable = false)
	@NotBlank
	private String SNILS;

	@Column(name = "email", nullable = false)
	@NotBlank
	private String Email;

	@Column(name = "phones")
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Phone> phones;

	@Override
	public ComplianceStatus getComplianceStatus() {
		return complianceStatus;
	}
}


