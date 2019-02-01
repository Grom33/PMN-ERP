package ru.gromov.customer.domain;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import ru.gromov.common.domain.AbstractBaseEntity;
import ru.gromov.customer.domain.enumitation.PhoneType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "phone")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Phone extends AbstractBaseEntity {

	@Column(name = "customer_id", nullable = false)
	@NotBlank
	private long customerId;

	@Column(name = "phone_type", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private PhoneType phoneType;

	@Column(name = "phone_number", nullable = false)
	@NotBlank
	private String number;

}

