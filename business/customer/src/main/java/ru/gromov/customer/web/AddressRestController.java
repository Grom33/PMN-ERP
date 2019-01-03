package ru.gromov.customer.web;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.service.AddressService;

import java.util.List;

@RestController
public class AddressRestController {
	@Autowired
	private AddressService addressService;

	@GetMapping(value = "/api/customers/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Address> read(@PathVariable int id){
		return addressService.getAllCustomerAddress(id);
	}

	@PostMapping(value = "/api/customers/address", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create( @RequestBody Address address){
		addressService.create(address);
	}

	@DeleteMapping(value = "/api/customers/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable int id){
		addressService.delete(id);
	}

}
