package com.otus.edu.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import ru.gromov.auth.domain.Account;
import ru.gromov.auth.service.AccountService;

@SpringBootTest
@DataMongoTest
@RunWith(SpringRunner.class)
public class AccountServiceTest extends AbstractMongoTest {
/*
    @Autowired
    private AccountService service;*/

  /*  @Autowired
    private MongoOperations operations;

    @Value("${spring.operations.mongodb.database}")
    private String collectionName;

    @Before
    public void init() {
        init(operations, collectionName);
    }

    @Test
    public void testFindAll() {
        assertThat(service.findAll().size()).isEqualTo(2);
    }

    @Test
    public void testFindById() {
        Account account = new Account();
        account.setId("1");
        assertThat(service.findById("1")).contains(account);
    }

    @Test
    public void testFindByNameOrEmail() {
        Account account = new Account();
        account.setId("1");
        account.setEmail("user@host.com");
        assertThat(service.findByNameOrEmail(account.getEmail()))
                .contains(account);
    }

    @Test
    @Ignore
    public void testUpdateById() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateVerification() {
        Account account = new Account();
        account.setId("1");
        assertThat(service.updateVerification(account.getId()))
                .contains(account).map(acc -> acc.getVerificationCode())
                .isNotEmpty();
    }

    @Test
    public void testCheckAccount() throws InterruptedException {
      *//*  int expiration = 1000;
        Account account = new Account();
        account.setId("2");
        account.setName("anonymous@google.com");

        Optional<Account> updatedAccountOptional = service
                .updateVerification(account.getId(), expiration);

        assertThat(updatedAccountOptional).contains(account)
                .map(acc -> acc.getVerificationCode()).isNotEmpty();

        Account updatedAccount = updatedAccountOptional.get();

        assertThat(service.checkAccount(account.getName(),
                updatedAccount.getVerificationCode())).isTrue();
        Thread.sleep(expiration + 1);
        assertThat(service.checkAccount(account.getName(),
                updatedAccount.getVerificationCode())).isFalse();*//*
    }
    @Test
    @Ignore
    public void testCreate() {
        fail("Not yet implemented");
    }*/
}
