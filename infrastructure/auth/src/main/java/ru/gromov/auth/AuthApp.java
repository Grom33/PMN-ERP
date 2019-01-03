package ru.gromov.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import ru.gromov.auth.config.JwtProperties;
import ru.gromov.auth.security.Roles;
import ru.gromov.auth.security.Users;
import ru.gromov.auth.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class AuthApp implements ApplicationRunner {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtProperties jwtProperties;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AuthApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        createDefaultUser(Users.ADMIN, Roles.ADMIN);
        createDefaultUser(Users.LIBRARIAN, Roles.LIBRARIAN);
        createDefaultUser(Users.CRITIC_DETECTIVE, Roles.CRITIC_DETECTIVE);
        createDefaultUser(Users.CRITIC_HUMOR, Roles.CRITIC_HUMOR);

        log.info("Library properties: ");
        log.info("{} = {}", "librari.jwt.header", jwtProperties.getHeader());
        log.info("{} = {}", "librari.jwt.prefix", jwtProperties.getPrefix());
        log.info("{} = {}", "librari.jwt.expiration",
                jwtProperties.getExpiration());
        log.info("{} = {}", "librari.jwt.sercret", jwtProperties.getSecret());
    }

    private void createDefaultUser(String userName, String role) {
        String defaultEmail = "@host.com";
        String defaultPassword = "pw";

        accountService.findByNameOrEmail(userName)
                .orElseGet(() -> accountService.createSystemUser(userName,
                        userName + defaultEmail, defaultPassword, role));
    }
}
