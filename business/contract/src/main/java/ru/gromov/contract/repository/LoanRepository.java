package ru.gromov.contract.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gromov.contract.domain.Loan;

import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	List<Loan> getAllByCustomerId(long customerId);

	long countAllByCustomerId(long customerId);
}
