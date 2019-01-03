package ru.gromov.customer.web;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.customer.domain.BankAccount;
import ru.gromov.customer.service.BankAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankAccountRestController {

	@Autowired
	private final BankAccountService bankAccountService;

	@GetMapping(value = "/api/customers/{id}/bankaccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BankAccount> read(@PathVariable int id) {
		return bankAccountService.getAllActiveCustomerBankAccounts(id);
	}

	@PostMapping(value = "/api/customers/bankaccount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody BankAccount bankAccount) {
		bankAccountService.create(bankAccount);
	}

	@DeleteMapping(value = "/api/customers/bankaccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable int id) {
		bankAccountService.delete(id);
	}

}
