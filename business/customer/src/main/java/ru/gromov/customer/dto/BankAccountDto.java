package ru.gromov.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class BankAccountDto {

    private Long id;

    private String beneficiaryName;

    private String accountNumber;

    private String bankName;

    private String bankINN;

    private String bankBIK;

    private String correspondentAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAccountDto bankAccountDto = (BankAccountDto) o;
        if (bankAccountDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccountDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
