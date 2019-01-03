package ru.gromov.customer.service;

import ru.gromov.customer.domain.BankAccount;


import java.util.List;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */
public interface BankAccountService {

	BankAccount get(long id);

	BankAccount update(BankAccount bankAccount);

	BankAccount create(BankAccount bankAccount);

	void delete(long id);

	List<BankAccount> getAllCustomerBankAccounts(long customerId);

	List<BankAccount> getAllActiveCustomerBankAccounts(long customerId);
}
