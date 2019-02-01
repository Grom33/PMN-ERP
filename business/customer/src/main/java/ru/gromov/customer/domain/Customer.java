package ru.gromov.customer.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.gromov.common.domain.AbstractBaseEntity;
import ru.gromov.common.domain.Supervised;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.enumitation.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
	private String maidenName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	@NotNull
	private Gender gender;

	@Column(name = "birth_place", nullable = false)
	@NotBlank
	private String birthPlace;

	@Column(name = "birthday", nullable = false)
	@NotNull
	private LocalDate birthday;

	@Column(name = "inn")
	private String iNN;

	@Column(name = "snils", nullable = false)
	@NotBlank
	private String sNILS;

	@Column(name = "email", nullable = false)
	@NotBlank
	private String email;

/*	@Column(name = "phones")
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Phone> phones;*/

	@Override
	public ComplianceStatus getComplianceStatus() {
		return complianceStatus;
	}
}


