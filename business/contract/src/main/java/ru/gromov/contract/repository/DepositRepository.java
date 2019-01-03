package ru.gromov.contract.repository;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gromov.contract.domain.Deposit;

import java.util.List;


@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

	List<Deposit> getAllByCustomerId(long customerId);


	long countByCustomerId(long customerId);

}
