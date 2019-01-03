package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.exception.AddressNotFoundExceptions;
import ru.gromov.customer.repository.AddressRepository;

import java.util.ConcurrentModificationException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private final AddressRepository addressRepository;


	@Override
	public Address create(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address update(final Address address) {
		if (read(address.getId()).getVersionUID() != address.getVersionUID()) {
			throw new ConcurrentModificationException();
		}
		return addressRepository.save(address);
	}

	@Transactional(readOnly = true)
	@Override
	public Address read(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundExceptions("Address not found"));
	}

	@Override
	public void delete(long id) {
		addressRepository.deleteAddressById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Address> getAllCustomerAddress(long customerId) {
		return addressRepository.findAllByCustomerId(customerId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Address> getAllActiveCustomerAddress(long customerId) {
		return addressRepository.findAllByCustomerIdAndActive(customerId, true);
	}
}
