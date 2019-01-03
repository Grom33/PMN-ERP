package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.customer.domain.Passport;

import java.util.List;

public interface PassportService {

	Passport get(long customerId);

	Passport update(Passport passport);

	Passport create(Passport passport);

	void delete(long id);

	List<Passport> getAllCustomerPassports(long customerId);

	List<Passport> getAllActiveCustomerPassports(long customerId);
}
