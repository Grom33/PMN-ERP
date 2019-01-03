package com.otus.edu.auth.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootConfiguration
@Import(MailSenderAutoConfiguration.class)
@ImportAutoConfiguration(classes = MailSenderAutoConfiguration.class)
public class TestConfig {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${library.auth.defaultEmail}")
    private String to;

}
