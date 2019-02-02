package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.dto.CustomerDto;
import ru.gromov.customer.exception.CustomerNotFoundException;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.repository.CustomerRepository;
import ru.gromov.customer.service.mapper.CustomerMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private final CustomerRepository customerRepository;

	private final CustomerMapper mapper;

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
		return customerRepository.findById(id)
						.orElseThrow(() ->
								new CustomerNotFoundException("Customer not found!"));
	}

	@Override
	public void delete(long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<CustomerDto> getAll() {
		return mapper.toDto(customerRepository.findAll());
	}

	@Override
	public List<CustomerDto> getAll(ComplianceStatus status) {
		return mapper.toDto(customerRepository.findAllByComplianceStatus(status));
	}

	@Override
	public List<CustomerDto> getAll(boolean active) {
		return mapper.toDto(customerRepository.findAllByActive(active));
	}

}
