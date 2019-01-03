package com.otus.edu.auth.service.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {


    @Value("${library.auth.defaultEmail}")
    private String to;
    
    @Test
    public void testSendSecreteCode() {
       // mailService.sendSecreteCode(to, "123");
    }

}
