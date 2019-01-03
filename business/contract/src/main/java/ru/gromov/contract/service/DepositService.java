package ru.gromov.contract.service;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.contract.domain.Deposit;

import java.util.List;

public interface DepositService {

	long create(Deposit deposit);

	void update(Deposit deposit);

	Deposit get(long id);

	List<Deposit> getByCustomerId(long customerId);

	List<Deposit> getAll();

	long getCount();

	long getCountByCustomer(long customerId);

	void delete(long id);

}
