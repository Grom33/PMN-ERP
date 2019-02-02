package ru.gromov.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
public class PassportDto {

    private Long id;

    protected String serial;

    protected String number;

    protected LocalDate date;

    protected String passportOffice;

    protected String officeCode;

    protected LocalDate expirationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PassportDto passportDto = (PassportDto) o;
        if (passportDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passportDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
