package ru.gromov.customer.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gromov.customer.domain.BankAccount;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	List<BankAccount> findAllByCustomerIdAndActive(long customerId, boolean active);

	List<BankAccount> findAllByCustomerId(long customerId);
}
