package ru.gromov.customer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Passport;
import ru.gromov.customer.exception.PassportNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/*
 *   Created by Gromov Vitaly (Grom33), 2019
 *   e-mail: mr.gromov.vitaly@gmail.com
 */public class PassportServiceImplTest extends AbstractServiceTest {

	@Autowired
	private PassportService passportService;

	@Test
	public void get() {
		Passport passport = passportService.get(1L);
		assertEquals(TestData.PASSPORT_IN_BASE_ID_1, passport);
	}

	@Test
	public void update() {
		Passport passport = passportService.get(TestData.PASSPORT_IN_BASE_ID_1.getId());
		passport.setPassportOffice("TestTEST");
		passportService.update(passport);
		Passport passportAfterEditAndSave = passportService.get(passport.getId());
		assertEquals("TestTEST", passportAfterEditAndSave.getPassportOffice());

	}

	@Test
	public void create() {
		Passport passport = passportService.create(TestData.PASSPORT_NEW);
		Passport passportAfterCreate = passportService.get(passport.getId());
		assertEquals(passport, passportAfterCreate);
	}

	@Test(expected = PassportNotFoundException.class)
	public void delete() {
		passportService.delete(1L);
		Passport passport = passportService.get(1L);
		System.out.println(passport);
	}

	@Test
	public void getAllCustomerPassports() {
		List<Passport> passports = passportService.getAllCustomerPassports(1L);
		assertThat(passports).containsOnlyElementsOf(TestData.PASSPORT_LIST_OF_CUSTOMER_1_ALL);
	}

	@Test
	public void getAllActiveCustomerPassports() {
		List<Passport> passports = passportService.getAllActiveCustomerPassports(1L);
		assertThat(passports).containsOnlyElementsOf(TestData.PASSPORT_LIST_OF_CUSTOMER_1);
	}

}