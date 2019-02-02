package ru.gromov.customer.service.mapper;

import org.mapstruct.Mapper;
import ru.gromov.customer.domain.Passport;
import ru.gromov.customer.dto.PassportDto;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface PassportMapper extends EntityMapper<PassportDto, Passport> {

    PassportDto toDto(Passport passport);

    Passport toEntity(PassportDto passportDTO);

    default Passport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Passport passport = new Passport();
        passport.setId(id);
        return passport;
    }
}
