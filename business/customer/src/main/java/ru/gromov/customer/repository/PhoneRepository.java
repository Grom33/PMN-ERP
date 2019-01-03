package ru.gromov.customer.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gromov.customer.domain.Phone;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

	List<Phone> getAllByCustomerId(long customerId);

	void deleteById(long id);

}
