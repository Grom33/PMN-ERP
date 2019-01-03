package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */


import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.domain.Passport;

import java.util.List;

public interface CustomerService {

	long create(Customer customer);

	Customer update(Customer customer);

	Customer get(long id);

	void delete(Customer customer);

	List<Customer> getAll();

	List<Customer> getAll(ComplianceStatus status);

	List<Customer> getAll(boolean active);
}
