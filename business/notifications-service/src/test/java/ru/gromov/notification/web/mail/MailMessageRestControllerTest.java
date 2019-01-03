package ru.gromov.notification.web.mail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { Application.class })
@SpringBootTest
//@WebAppConfiguration
public class MailMessageRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("mailRestController"));
	}

	@Test
	@WithMockUser(username="Ivan", password="password", roles={"USER"})
	public void checkMailStatus() throws Exception {
		mockMvc.perform(get("/api/mail/checkMessageStatus/37fef3a7-13c7-4198-9c85-95bc887f9ffb"))
				.andExpect(status().isOk());
	}

	@Test
	public void addMessage() {

	}

	@Test
	@WithMockUser(username="Ivan", password="password", roles={"USER"})
	public void getMessage() throws Exception {
		mockMvc.perform(get("/api/mail/"))
				.andExpect(status().isOk());
	}

}