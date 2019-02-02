package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */



import ru.gromov.customer.domain.Address;
import ru.gromov.customer.dto.AddressDto;

import java.util.List;

public interface AddressService {
	Address create(Address address);

	Address update(Address address);

	Address read(long id);

	void delete(long id);

	List<Address> getAllCustomerAddress(long customerId);

	List<AddressDto> getAllActiveCustomerAddress(long customerId);

}
