package ru.gromov.common.domain;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.common.domain.compliance.ComplianceStatus;

public interface Supervised {

	ComplianceStatus getComplianceStatus();

	default boolean isAllowed() {
		return ComplianceStatus.GRANTED.equals(getComplianceStatus());
	}
}
