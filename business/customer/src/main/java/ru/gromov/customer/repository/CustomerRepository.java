package ru.gromov.customer.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAllByComplianceStatus(ComplianceStatus complianceStatus);

	List<Customer> findAllByActive(boolean active);

	void deleteById(int id);

}
