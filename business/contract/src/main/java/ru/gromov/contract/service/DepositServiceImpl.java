package ru.gromov.contract.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.contract.domain.Deposit;
import ru.gromov.contract.exception.DepositNotFoundException;
import ru.gromov.contract.repository.DepositRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepositServiceImpl implements DepositService{

	@Autowired
	private final DepositRepository depositRepository;


	@Override
	public long create(Deposit deposit) {
		return depositRepository.save(deposit).getId();
	}

	@Override
	public void update(Deposit deposit) {
		depositRepository.save(deposit);
	}

	@Override
	public Deposit get(long id) {
		return depositRepository.findById(id)
				.orElseThrow(()->new DepositNotFoundException("Deposit not found!"));
	}

	@Override
	public List<Deposit> getByCustomerId(long customerId) {
		return depositRepository.getAllByCustomerId(customerId);
	}

	@Override
	public List<Deposit> getAll() {
		return depositRepository.findAll();
	}

	@Override
	public long getCount() {
		return depositRepository.count();
	}

	@Override
	public long getCountByCustomer(long customerId) {
		return depositRepository.countByCustomerId(customerId);
	}

	@Override
	public void delete(long id) {
		depositRepository.deleteById(id);
	}
}
