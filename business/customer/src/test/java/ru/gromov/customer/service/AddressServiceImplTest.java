package ru.gromov.customer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.exception.AddressNotFoundExceptions;

import java.util.List;

import static org.junit.Assert.*;

/*
 *   Created by Gromov Vitaly (Grom33), 2019
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceImplTest extends AbstractServiceTest{

	@Autowired
	AddressService addressService;

	@Test
	public void create() {
		Address address = addressService.create(ADDRESS_NEW);
	}

	@Test
	public void update() {
		Address address = addressService.read(ADDRESS_IN_BASE_ID_1.getId());
		address.setStreet("TestStreet");
		addressService.update(address);
		Address addressAfterEditAndSave = addressService.read(address.getId());
		assertEquals("TestStreet", addressAfterEditAndSave.getStreet());
	}

	@Test
	public void read() {
		Address address = addressService.read(1L);
		System.out.println(address);
	}

	@Test(expected = AddressNotFoundExceptions.class)
	public void delete() {
		addressService.delete(1L);
		Address address = addressService.read(1L);
	}

	@Test
	public void getAllCustomerAddress() {
		List<Address> address = addressService.getAllCustomerAddress(1);
		assertEquals(2, address.size());
		assertEquals(ADDRESS_LIST_OF_CUSTOMER_1, address);
	}

	@Test
	public void getAllActiveCustomerAddress() {
		List<Address> address = addressService.getAllActiveCustomerAddress(1L);
		System.out.println(address);
	}
}