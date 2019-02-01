package ru.gromov.customer.service.mapper;

import org.mapstruct.*;
import ru.gromov.customer.domain.Customer;
import ru.gromov.customer.dto.CustomerDto;


/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {


    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "phones", ignore = true)
    @Mapping(target = "passports", ignore = true)
    @Mapping(target = "bankAccounts", ignore = true)
    Customer toEntity(CustomerDto customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
