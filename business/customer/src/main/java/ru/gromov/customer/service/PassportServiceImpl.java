package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.customer.domain.Passport;
import ru.gromov.customer.repository.PassportRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PassportServiceImpl implements PassportService {

	@Autowired
	private final PassportRepository passportRepository;

	@Override
	public Passport get(long customerId) {
		return passportRepository.getOne(customerId);
	}

	@Override
	public Passport update(Passport passport) {
		return passportRepository.save(passport);
	}

	@Override
	public void create(Passport passport) {
		passportRepository.save(passport);
	}

	@Override
	public void delete(long id) {
		passportRepository.deletePassportById(id);
	}


	@Override
	public List<Passport> getAllCustomerPassports(long customerId) {
		return passportRepository.findAllByCustomerId(customerId);
	}
}
