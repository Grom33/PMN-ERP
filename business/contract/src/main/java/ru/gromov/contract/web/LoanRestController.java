package ru.gromov.contract.web;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.contract.domain.Loan;
import ru.gromov.contract.service.LoanService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoanRestController {

	@Autowired
	private LoanService loanService;

	@GetMapping(value = "/api/loans", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getAll() {
		return loanService.getAll();
	}

	@GetMapping(value = "/api/loans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Loan getById(@PathVariable long id) {
		return loanService.get(id);
	}

	@GetMapping(value = "/api/loans/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getByCustomerId(@PathVariable long customerId) {
		return loanService.getByCustomerId(customerId);
	}

	@GetMapping(value = "/api/loans/customer/{customerId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCountByCustomerId(@PathVariable long customerId) {
		return loanService.getCountByCustomer(customerId);
	}

	@GetMapping(value = "/api/loans/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCount() {
		return loanService.getCount();
	}

	@PostMapping(value = "/api/loans", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Loan loan) {
		log.info("Loan is: {}", loan);
		loanService.create(loan);
	}

	@DeleteMapping(value = "/api/loans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable long id) {
		loanService.delete(id);
	}

	@PutMapping(value = "/api/loans", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Loan loan) {
		log.info("Loan is: {}", loan);
		loanService.update(loan);
	}


}
