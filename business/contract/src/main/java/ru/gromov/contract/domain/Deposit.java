package ru.gromov.contract.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import ru.gromov.common.domain.AbstractBaseEntity;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.common.domain.contract.PaymentType;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deposit")
public class Deposit extends AbstractBaseEntity {

	@Column(name = "doc_number", nullable = false)
	@NotBlank
	private String number;

	@Column(name = "date_creation", nullable = false)
	private LocalDate dateCreation;

	@Column(name = "customer_id", nullable = false)
	@NotNull
	private long customerId;

	@Column(name = "sum", nullable = false)
	@NotNull
	private BigDecimal sum;

	@Column(name = "term", nullable = false)
	@NotNull
	private int term;

	@Column(name = "rate", nullable = false)
	@NotNull
	private BigDecimal rate;

	@Enumerated(EnumType.STRING)
	@Column(name = "interest_payment", nullable = false)
	private PaymentType interestPayment;

	@Enumerated(EnumType.STRING)
	@Column(name = "complianceStatus", nullable = false)
	private ComplianceStatus complianceStatus;

	@Column(name = "registered", nullable = false)
	private LocalDateTime registered;

	@Column(name = "active", nullable = false)
	private boolean active;
}
