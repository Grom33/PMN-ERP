package ru.gromov.customer.domain;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
import ru.gromov.common.domain.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "bank_account")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BankAccount extends AbstractBaseEntity {

	@Column(name = "customer_id", nullable = false)
	@NotBlank
	private long customerId;

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
