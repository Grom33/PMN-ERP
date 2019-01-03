package com.otus.edu.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.gromov.auth.config.JwtProperties;
import ru.gromov.auth.controller.AdminController;
import ru.gromov.auth.domain.Account;
import ru.gromov.auth.service.AccountService;
import ru.gromov.auth.service.LibraryUserDetailsService;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(secure = false)
public class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryUserDetailsService userDetailService;

    @MockBean
    private AccountService service;

    @MockBean
    private JwtProperties jwtProperties;

    private final String baseUrl = "/account/";

    @Before
    public void init() {
        Account account = new Account();
        account.setId("1");
        account.setName("user");
        account.setRoles("ROLE1,ROLE2");

        given(service.findAll()).willReturn(Arrays.asList(account));
    }

    @Test
    public void list() throws Exception {
        assertThat(service.findAll()).hasSize(1);
        mvc.perform(get(baseUrl)).andExpect(content().json(
                "[{\"id\":\"1\",\"name\":\"user\",\"roles\":[\"ROLE1\",\"ROLE2\"]}]"));
    }
}
