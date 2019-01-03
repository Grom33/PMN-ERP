package ru.gromov.customer.web;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.customer.domain.Passport;
import ru.gromov.customer.service.PassportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PassportRestController {

	@Autowired
	private final PassportService passportService;

	@GetMapping(value = "/api/customers/{id}/passport", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Passport> read(@PathVariable int id) {
		return passportService.getAllActiveCustomerPassports(id);
	}

	@PostMapping(value = "/api/customers/passport", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Passport passport) {
		passportService.create(passport);
	}

	@DeleteMapping(value = "/api/customers/passport/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable int id) {
		passportService.delete(id);
	}

}
