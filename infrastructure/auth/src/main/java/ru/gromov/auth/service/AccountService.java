package ru.gromov.auth.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.gromov.auth.domain.Account;
import ru.gromov.auth.repository.AccountRepository;
import ru.gromov.auth.security.Roles;

@Service
@Transactional
public class AccountService {

    private AccountRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Optional<Account> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Account> findByNameOrEmail(String username) {
        return repository.findAccount(username);
    }

    public Optional<Account> updateById(String id, Account account) {
        return repository.findById(id).map(acc -> {
            account.setId(acc.getId());
            return repository.save(account);
        });
    }

    public Optional<Account> updateVerification(String id) {
        // code expiration = 2 minute
        return updateVerification(id, 1000 * 60 * 2);
    }

    Optional<Account> updateVerification(String id, int expiration) {
        return repository.findById(id).map(account -> {
            String code = Integer.toString(new Random().nextInt(8999) + 1000);

            account.setVerificationCode(code);
            account.setVerificationTime(
                    System.currentTimeMillis() + expiration);
            System.out.println("Val_code "+code);
            //mailService.sendSecreteCode(account.getEmail(), code);
            return repository.save(account);
        });
    }

    public boolean checkAccount(String username, String code) {
        return findByNameOrEmail(username).flatMap(acc -> repository
                .findByIdAndVerificationCodeAndVerificationTimeGreaterThan(
                        acc.getId(), code, System.currentTimeMillis()))
                .isPresent();
    }

    public Account createSystemUser(String name, String email, String password,
            String... roles) {
        Account newAccount = new Account();

        newAccount.setName(name);
        newAccount.setEmail(email);
        newAccount.setPassword(passwordEncoder.encode(password));
        newAccount.setRoles(Optional.ofNullable(roles)
                .map(v -> Stream.of(v).collect(Collectors.joining(",")))
                .orElse(Roles.USER));

        return repository.save(newAccount);
    }

    public Account createUser(String username, String password,
            String... roles) {
        Account newAccount = new Account();

        int atIndex = username.indexOf("@");

        newAccount.setName(
                atIndex > 0 ? username.substring(0, atIndex) : username);
        newAccount.setEmail(atIndex > 0 ? username : null);
        newAccount.setPassword(passwordEncoder.encode(password));
        newAccount.setRoles(Optional.ofNullable(roles)
                .map(v -> Stream.of(v).collect(Collectors.joining(",")))
                .orElse(Roles.USER));

        return repository.save(newAccount);
    }
}
