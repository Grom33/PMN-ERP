package ru.gromov.contract.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gromov.contract.domain.Loan;
import ru.gromov.contract.exception.LoanNotFoundException;
import ru.gromov.contract.repository.LoanRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;

	@Override
	public long create(Loan loan) {
		return loanRepository.save(loan).getId();
	}

	@Override
	public void update(Loan loan) {
		loanRepository.save(loan);
	}

	@Override
	public Loan get(long id) {
		return loanRepository.findById(id)
				.orElseThrow(() -> new LoanNotFoundException("Loan not found!"));
	}

	@Override
	public List<Loan> getByCustomerId(long customerId) {
		return loanRepository.getAllByCustomerId(customerId);
	}

	@Override
	public List<Loan> getAll() {
		return loanRepository.findAll();
	}

	@Override
	public long getCount() {
		return loanRepository.count();
	}

	@Override
	public long getCountByCustomer(long customerId) {
		return loanRepository.countAllByCustomerId(customerId);
	}

	@Override
	public void delete(long id) {
		loanRepository.deleteById(id);
	}
}
