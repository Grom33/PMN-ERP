package ru.gromov.customer.domain;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.gromov.common.domain.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passport")
@Entity
public class Passport extends AbstractBaseEntity {

	@JsonBackReference(value = "customer")
	@JoinColumn(name = "customer_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

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
