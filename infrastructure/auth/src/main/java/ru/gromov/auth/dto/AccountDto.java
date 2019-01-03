package ru.gromov.auth.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ru.gromov.auth.domain.Account;

import lombok.Data;

@Data
public class AccountDto {
    private final String id;
    private final String name;
    private final String email;
    private final List<String> roles;

    public static AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getName(),
                account.getEmail(),
                Arrays.asList(Optional.ofNullable(account.getRoles())
                        .map(rls -> rls.split(",")).orElse(new String[0])));
    }
}
