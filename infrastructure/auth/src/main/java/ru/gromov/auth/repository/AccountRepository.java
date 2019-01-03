package ru.gromov.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.gromov.auth.domain.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByNameOrEmail(String username, String email);

    Optional<Account> findByIdAndVerificationCodeAndVerificationTimeGreaterThan(
            String id, String code, long time);

    default Optional<Account> findAccount(String username) {
        return findByNameOrEmail(username, username);
    }
}
