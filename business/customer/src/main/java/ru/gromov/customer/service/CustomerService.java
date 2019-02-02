package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */


import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.domain.Passport;
import ru.gromov.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

	long create(Customer customer);

	Customer update(Customer customer);

	Customer get(long id);

	void delete(long id);

	List<CustomerDto> getAll();

	List<CustomerDto> getAll(ComplianceStatus status);

	List<CustomerDto> getAll(boolean active);
}
