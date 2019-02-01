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

	CustomerDto update(Customer customer);

	CustomerDto get(long id);

	void delete(long id);

	List<Customer> getAll();

	List<Customer> getAll(ComplianceStatus status);

	List<Customer> getAll(boolean active);
}
