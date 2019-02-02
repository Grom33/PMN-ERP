package ru.gromov.customer.service.mapper;

import org.mapstruct.Mapper;
import ru.gromov.customer.domain.Phone;
import ru.gromov.customer.dto.PhoneDto;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface PhoneMapper extends EntityMapper<PhoneDto, Phone> {

    PhoneDto toDto(Phone phone);

    Phone toEntity(PhoneDto phoneDto);

    default Phone fromId(Long id) {
        if (id == null) {
            return null;
        }
        Phone phone = new Phone();
        phone.setId(id);
        return phone;
    }
}
