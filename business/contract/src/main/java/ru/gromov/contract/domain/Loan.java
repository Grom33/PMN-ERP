package ru.gromov.contract.domain;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.*;
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
@Table(name = "loan")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Loan extends AbstractBaseEntity {

	@Column(name = "customer_id", nullable = false)
	@NotNull
	private long customerId;

	@Column(name = "doc_number", nullable = false)
	@NotBlank
	private String number;

	@Column(name = "date_creation", nullable = false)
	private LocalDate dateCreation;

	@Column(name = "payout_date")
	private LocalDate payoutDate;

	@Column(name = "payoff_date")
	private LocalDate payoffDate;

	@Column(name = "sum", nullable = false)
	@NotNull
	private BigDecimal sum;

	@Column(name = "term", nullable = false)
	@NotNull
	private int term;

	@Column(name = "rate", nullable = false)
	@NotNull
	private BigDecimal rate;

	@Column(name = "full_loan_coast")
	private BigDecimal fullLoanCoast;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private LoanStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_interval", nullable = false)
	private PaymentType paymentInterval;

	@Enumerated(EnumType.STRING)
	@Column(name = "complianceStatus", nullable = false)
	private ComplianceStatus complianceStatus;

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "registered", nullable = false)
	private LocalDateTime registered;

}

