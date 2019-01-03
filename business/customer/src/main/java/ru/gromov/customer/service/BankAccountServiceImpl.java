package ru.gromov.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.customer.domain.BankAccount;
import ru.gromov.customer.exception.BankAccountNotFountException;
import ru.gromov.customer.repository.BankAccountRepository;

import java.util.List;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private final BankAccountRepository bankAccountRepository;

	@Override
	public BankAccount get(long id) {
		return bankAccountRepository.findById(id)
				.orElseThrow(() -> new BankAccountNotFountException("Bank account not found!"));
	}

	@Override
	public BankAccount update(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount create(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public void delete(long id) {
		bankAccountRepository.deleteById(id);
	}

	@Override
	public List<BankAccount> getAllCustomerBankAccounts(long customerId) {
		return bankAccountRepository.findAllByCustomerId(customerId);
	}

	@Override
	public List<BankAccount> getAllActiveCustomerBankAccounts(long customerId) {
		return bankAccountRepository.findAllByCustomerIdAndActive(customerId, true);
	}
}
