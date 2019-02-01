package ru.gromov.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.enumitation.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CustomerDto implements Serializable {

    private Long id;

    private UUID customerUuid;

    private ComplianceStatus complianceStatus;

    private String name;

    private String middleName;

    private String surname;

    private String maidenName;

    private Gender gender;

    private String birthPlace;

    private LocalDate birthday;

    private String iNN;

    private String sNILS;

    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDto customerDTO = (CustomerDto) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
