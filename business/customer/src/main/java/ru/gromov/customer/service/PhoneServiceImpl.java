package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.customer.domain.Phone;
import ru.gromov.customer.exception.PhoneNotFoundException;
import ru.gromov.customer.repository.PhoneRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService{

	@Autowired
	private final PhoneRepository phoneRepository;

	@Override
	public Phone get(long id) {
		return phoneRepository.findById(id)
				.orElseThrow(()-> new PhoneNotFoundException("Phone not found!"));
	}

	@Override
	public Phone update(Phone phone) {
		return phoneRepository.save(phone);
	}

	@Override
	public Phone create(Phone phone) {
		return phoneRepository.save(phone);
	}

	@Override
	public void delete(long id) {
		phoneRepository.deleteById(id);
	}

	@Override
	public List<Phone> getAllCustomerPhones(long customerId) {
		return phoneRepository.getAllByCustomerId(customerId);
	}
}
