package ru.gromov.customer.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gromov.customer.domain.Address;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findAllByCustomerIdAndActive(long customerId, boolean active);

	List<Address> findAllByCustomerId(long customerId);

	void deleteAddressById(long id);

}
