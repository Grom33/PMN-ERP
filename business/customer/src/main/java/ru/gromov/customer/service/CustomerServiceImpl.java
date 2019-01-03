package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.exception.CustomerNotFoundException;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.repository.CustomerRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private final CustomerRepository customerRepository;

	@Override
	public long create(Customer customer) {
		return customerRepository.save(customer).getId();
	}

	@Override
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer get(long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
	}

	@Override
	public void delete(long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> getAll(ComplianceStatus status) {
		return customerRepository.findAllByComplianceStatus(status);
	}

	@Override
	public List<Customer> getAll(boolean active) {
		return customerRepository.findAllByActive(active);
	}

}
