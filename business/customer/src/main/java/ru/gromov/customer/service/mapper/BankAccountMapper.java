package ru.gromov.customer.service.mapper;

import ru.gromov.customer.domain.BankAccount;
import ru.gromov.customer.dto.BankAccountDto;

public interface BankAccountMapper extends EntityMapper<BankAccountDto, BankAccount> {

    BankAccountDto toDto(BankAccount bankAccount);

    BankAccount toEntity(BankAccountDto bankAccountDto);

    default BankAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(id);
        return bankAccount;
    }
}
