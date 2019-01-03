package ru.gromov.contract.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.contract.domain.Loan;

import java.util.List;

public interface LoanService {

	long create(Loan loan);

	void update(Loan loan);

	Loan get(long id);

	List<Loan> getByCustomerId(long customerId);

	List<Loan> getAll();

	long getCount();

	long getCountByCustomer(long customerId);

	void delete(long id);
}
