package ru.gromov.customer.web;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.service.CustomerService;

import java.util.List;

@RestController
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;


	@GetMapping(value = "/api/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer read(@PathVariable int id){
		return customerService.get(id);
	}

	@PostMapping(value = "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create( @RequestBody Customer customer){
		customerService.create(customer);
	}

	@GetMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> readAll(){
		return customerService.getAll();
	}

}
