package ru.gromov.customer.domain;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import ru.gromov.common.domain.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "passport")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Passport extends AbstractBaseEntity {

	@Column(name = "customer_id", nullable = false)
	@NotBlank
	private long customerId;

	@Column(name = "pass_serial", nullable = false)
	@NotBlank
	protected String serial;

	@Column(name = "pass_number", nullable = false)
	@NotBlank
	protected String number;

	@Column(name = "pass_date", nullable = false)
	@NotBlank
	protected LocalDate date;

	@Column(name = "passport_office", nullable = false)
	@NotBlank
	protected String passportOffice;

	@Column(name = "office_code", nullable = false)
	@NotBlank
	protected String officeCode;

	@Column(name = "expiration_date", nullable = false)
	@NotBlank
	protected LocalDate expirationDate;

}
