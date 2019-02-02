package ru.gromov.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.gromov.customer.domain.enumitation.PhoneType;

import java.util.Objects;

@Getter
@Setter
@ToString
public class PhoneDto {

    private Long id;

    private PhoneType phoneType;

    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhoneDto phoneDto = (PhoneDto) o;
        if (phoneDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phoneDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
