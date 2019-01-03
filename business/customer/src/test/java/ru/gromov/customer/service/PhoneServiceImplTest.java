package ru.gromov.customer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Phone;
import ru.gromov.customer.exception.PhoneNotFoundException;

import javax.validation.constraints.AssertFalse;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
/*
 *   Created by Gromov Vitaly (Grom33), 2019
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class PhoneServiceImplTest extends AbstractServiceTest {

	@Autowired
	private PhoneService phoneService;

	@Test
	public void get() {
		Phone phone = phoneService.get(1L);
		assertEquals(TestData.PHONE_IN_BASE_ID_1, phone);
	}

	@Test
	public void update() {
		Phone phone = phoneService.get(TestData.PHONE_IN_BASE_ID_1.getId());
		phone.setNumber("910-000000000000");
		phoneService.update(phone);
		Phone phoneAfterEditAndSave = phoneService.get(phone.getId());
		assertEquals("910-000000000000", phoneAfterEditAndSave.getNumber());
	}

	@Test
	public void create() {
		Phone phone = phoneService.create(TestData.PHONE_NEW);
		Phone phoneAfterCreate = phoneService.get(phone.getId());
		assertEquals(phone, phoneAfterCreate);
	}

	@Test(expected = PhoneNotFoundException.class)
	public void delete() {
		phoneService.delete(1L);
		Phone phone = phoneService.get(1L);
	}

	@Test
	public void getAllCustomerPhones() {
		List<Phone> phones = phoneService.getAllCustomerPhones(1L);
		assertThat(phones).containsOnlyElementsOf(TestData.PHONE_LIST_OF_CUSTOMER_1);
	}
}