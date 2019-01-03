package ru.gromov.customer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.exception.CustomerNotFoundException;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

public class CustomerServiceImplTest extends AbstractServiceTest {

	@Autowired
	private CustomerService customerService;

	@Test
	public void create() {
		Customer customer = customerService.get(1L);
		assertEquals(TestData.CUSTOMER_IN_BASE_ID_1, customer);
	}

	@Test
	public void update() {
		Customer customer = customerService.get(TestData.CUSTOMER_IN_BASE_ID_1.getId());
		customer.setName("TestName");
		customerService.update(customer);
		Customer customerAfterEditAndSave = customerService.get(customer.getId());
		assertEquals("TestName", customerAfterEditAndSave.getName());
	}

	@Test
	public void get() {
		Customer customer = customerService.get(1L);
		assertEquals(TestData.CUSTOMER_IN_BASE_ID_1, customer);
	}

	@Test(expected = CustomerNotFoundException.class)
	public void delete() {
		customerService.delete(1L);
		Customer customer = customerService.get(1L);
	}

	@Test
	public void getAll() {
		assertEquals(TestData.CUSTOMER_COUNT,
				customerService.getAll().size());
	}

	@Test
	public void getAllActive() {
		assertEquals(TestData.ACTIVE_CUSTOMER_COUNT,
				customerService.getAll(true).size());
	}

	@Test
	public void getAllBlocked() {
		assertEquals(TestData.BLOCKED_CUSTOMER_COUNT,
				customerService.getAll(ComplianceStatus.BLOCKED).size());
	}
}