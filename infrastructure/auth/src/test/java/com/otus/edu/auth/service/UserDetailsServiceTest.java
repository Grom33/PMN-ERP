package com.otus.edu.auth.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gromov.auth.service.LibraryUserDetailsService;

@SpringBootTest
@DataMongoTest
@RunWith(SpringRunner.class)
public class UserDetailsServiceTest extends AbstractMongoTest {

    @Autowired
    private LibraryUserDetailsService userDetailsService;

    @Autowired
    private MongoOperations operations;

    @Value("${spring.operations.mongodb.database}")
    private String collectionName;

    @Before
    public void init() {
        init(operations, collectionName);
    }

    @Test
    public void testLoadUserByUsername() {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername("user@host.com");
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getAuthorities()).hasSize(1);
    }
}
