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

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bank_account")
@Entity
public class BankAccount extends AbstractBaseEntity {

	@JsonBackReference(value = "customer")
	@JoinColumn(name = "customer_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_inn")
	private String bankINN;

	@Column(name = "bank_bik")
	private String bankBIK;

	@Column(name = "correspondent_account")
	private String correspondentAccount;

}
