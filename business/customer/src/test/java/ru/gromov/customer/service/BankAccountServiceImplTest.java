package ru.gromov.customer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.BankAccount;
import ru.gromov.customer.exception.BankAccountNotFountException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */
public class BankAccountServiceImplTest extends AbstractServiceTest{

	@Autowired
	private BankAccountService bankAccountService;

	@Test
	public void get() {
		BankAccount bankAccount = bankAccountService.get(1L);
		assertEquals(TestData.BANK_ACCOUNT_IN_BASE_ID_1, bankAccount);
	}

	@Test
	public void update() {
		BankAccount bankAccount = bankAccountService.get(TestData.BANK_ACCOUNT_IN_BASE_ID_1.getId());
		bankAccount.setBeneficiaryName("TestName");
		bankAccountService.update(bankAccount);
		BankAccount bankAccountAfterEditAndSave = bankAccountService.get(bankAccount.getId());
		assertEquals("TestName", bankAccountAfterEditAndSave.getBeneficiaryName());
	}

	@Test
	public void create() {
		BankAccount bankAccount = bankAccountService.create(TestData.BANK_ACCOUNT_NEW);
		BankAccount bankAccountAfterCreate = bankAccountService.get(bankAccount.getId());
		assertEquals(bankAccount, bankAccountAfterCreate);
	}

	@Test(expected = BankAccountNotFountException.class)
	public void delete() {
		bankAccountService.delete(1L);
		BankAccount bankAccount = bankAccountService.get(1L);
	}

	@Test
	public void getAllCustomerBankAccounts() {
		List<BankAccount> bankAccounts =
				bankAccountService.getAllCustomerBankAccounts(1L);
		assertThat(bankAccounts)
				.containsOnlyElementsOf(TestData.BANK_ACCOUNT_LIST_OF_CUSTOMER_1_ALL);
	}

	@Test
	public void getAllActiveCustomerBankAccounts() {
		List<BankAccount> bankAccounts =
				bankAccountService.getAllActiveCustomerBankAccounts(1L);
		assertThat(bankAccounts).containsOnlyElementsOf(TestData.BANK_ACCOUNT_LIST_OF_CUSTOMER_1);
	}
}