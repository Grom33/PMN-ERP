package operations.domain;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gromov.common.domain.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment extends AbstractBaseEntity {

	@Column(name = "payment_date", nullable = false)
	@NotBlank
	private LocalDate Date;

	@Column(name = "sum", nullable = false)
	@NotBlank
	private BigDecimal Sum;

	@Column(name = "customer_id", nullable = false)
	@NotBlank
	private int CustomerId;

	@Column(name = "contract_id", nullable = false)
	@NotBlank
	private int ContractId;

	@Column(name = "payment_document", nullable = false)
	@NotBlank
	private String PaymentDocument;

	@Column(name = "target", nullable = false)
	@NotBlank
	private PaymentTarget target;

	@Column(name = "flow", nullable = false)
	@NotBlank
	private PaymentDirection flow;
}
