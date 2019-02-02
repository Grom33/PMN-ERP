package ru.gromov.customer.service.mapper;

import org.mapstruct.Mapper;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.dto.AddressDto;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface AddressMapper extends EntityMapper<AddressDto, Address> {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
