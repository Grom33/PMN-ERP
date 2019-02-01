package ru.gromov.customer.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.gromov.common.domain.AbstractBaseEntity;
import ru.gromov.customer.domain.enumitation.AddressType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
@Entity
public class Address extends AbstractBaseEntity {

	@JsonBackReference(value = "customer")
	@JoinColumn(name = "customer_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

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