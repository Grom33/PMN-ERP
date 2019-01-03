package ru.gromov.customer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.exception.AddressNotFoundExceptions;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/*
 *   Created by Gromov Vitaly (Grom33), 2019
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class AddressServiceImplTest extends AbstractServiceTest {

	@Autowired
	private AddressService addressService;

	@Test
	public void read() {
		Address address = addressService.read(1L);
		assertEquals(TestData.ADDRESS_IN_BASE_ID_1, address);
	}

	@Test
	public void create() {
		Address address = addressService.create(TestData.ADDRESS_NEW);
		Address addressAfterCreate = addressService.read(address.getId());
		assertEquals(address, addressAfterCreate);
	}

	@Test
	public void update() {
		Address address = addressService.read(TestData.ADDRESS_IN_BASE_ID_1.getId());
		address.setStreet("TestStreet");
		addressService.update(address);
		Address addressAfterEditAndSave = addressService.read(address.getId());
		assertEquals("TestStreet", addressAfterEditAndSave.getStreet());
	}


	@Test(expected = AddressNotFoundExceptions.class)
	public void delete() {
		addressService.delete(1L);
		Address address = addressService.read(1L);
	}

	@Test
	public void getAllCustomerAddress() {
		List<Address> address = addressService.getAllCustomerAddress(1);
		assertThat(address).containsOnlyElementsOf(TestData.ADDRESS_LIST_OF_CUSTOMER_1_ALL);
	}

	@Test
	public void getAllActiveCustomerAddress() {
		List<Address> address = addressService.getAllActiveCustomerAddress(1L);
		assertThat(address).containsOnlyElementsOf(TestData.ADDRESS_LIST_OF_CUSTOMER_1);
	}
}