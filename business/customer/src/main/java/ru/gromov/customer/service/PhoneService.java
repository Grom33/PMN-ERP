package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.customer.domain.Phone;

import java.util.List;

public interface PhoneService {
	Phone get(long id);

	Phone update(Phone phone);

	void create(Phone phone);

	void delete(long id);

	List<Phone> getAllCustomerPhones(long customerId);
}
