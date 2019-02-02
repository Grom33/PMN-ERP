package ru.gromov.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.gromov.customer.domain.enumitation.AddressType;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Getter
@Setter
public class AddressDto implements Serializable {

    private Long id;

    private String zipCode;

    private String region;

    private String city;

    private String street;

    private String house;

    private String flat;

    private AddressType addressType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDto addressDTO = (AddressDto) o;
        if (addressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
