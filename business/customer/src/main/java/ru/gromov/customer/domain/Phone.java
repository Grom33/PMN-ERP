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
import ru.gromov.customer.domain.enumitation.PhoneType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "phone")
public class Phone extends AbstractBaseEntity {

	@JsonBackReference(value = "customer")
	@JoinColumn(name = "customer_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	@Column(name = "phone_type", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private PhoneType phoneType;

	@Column(name = "phone_number", nullable = false)
	@NotBlank
	private String number;

}

