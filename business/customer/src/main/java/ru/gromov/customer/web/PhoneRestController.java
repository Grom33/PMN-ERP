package ru.gromov.customer.web;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.customer.domain.Phone;
import ru.gromov.customer.service.PhoneService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhoneRestController {

	@Autowired
	PhoneService phoneService;

	@GetMapping(value = "/api/customers/{id}/phones", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Phone> read(@PathVariable int id) {
		return phoneService.getAllCustomerPhones(id);
	}

	@PostMapping(value = "/api/customers/phones", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody Phone phone) {
		phoneService.create(phone);
	}

	@DeleteMapping(value = "/api/customers/phone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable int id) {
		phoneService.delete(id);
	}


}
