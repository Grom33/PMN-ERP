package ru.gromov.contract.web;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.contract.domain.Deposit;
import ru.gromov.contract.service.DepositService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepositRestController {

	@Autowired
	DepositService depositService;

	@GetMapping(value = "/api/deposits", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Deposit> getAll() {
		return depositService.getAll();
	}

	@GetMapping(value = "/api/deposits/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Deposit getById(@PathVariable long id) {
		return depositService.get(id);
	}

	@GetMapping(value = "/api/deposits/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Deposit> getByCustomerId(@PathVariable long customerId) {
		return depositService.getByCustomerId(customerId);
	}

	@GetMapping(value = "/api/deposits/customer/{customerId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCountByCustomerId(@PathVariable long customerId) {
		return depositService.getCountByCustomer(customerId);
	}

	@GetMapping(value = "/api/deposits/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public long getCount() {
		return depositService.getCount();
	}

	@PostMapping(value = "/api/deposits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Deposit deposit) {
		depositService.create(deposit);
	}

	@DeleteMapping(value = "/api/deposits/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable long id) {
		depositService.delete(id);
	}

	@PutMapping(value = "/api/deposits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Deposit deposit) {
		depositService.update(deposit);
	}
}
