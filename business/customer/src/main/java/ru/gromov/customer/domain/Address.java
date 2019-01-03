package ru.gromov.customer.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import ru.gromov.common.domain.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@ToString(callSuper = true)
public class Address extends AbstractBaseEntity {

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "zipCode", nullable = false)
	@NotBlank
	private String zipCode;

	@Column(name = "region", nullable = false)
	@NotBlank
	private String region;

	@Column(name = "city", nullable = false)
	@NotBlank
	private String city;

	@Column(name = "street", nullable = false)
	@NotBlank
	private String street;

	@Column(name = "house", nullable = false)
	@NotBlank
	private String house;

	@Column(name = "flat", nullable = false)
	@NotBlank
	private String flat;

	@Column(name = "addr_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

}