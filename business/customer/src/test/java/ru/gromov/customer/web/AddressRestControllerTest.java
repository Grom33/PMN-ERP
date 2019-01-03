package ru.gromov.customer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.service.AddressService;

import javax.annotation.PostConstruct;

import static org.junit.Assert.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

public class AddressRestControllerTest extends AbstractWebTest{

	@Autowired
	private AddressService addressService;

	private static final String REST_URL = "/api/customers/";

	@Test
	@SneakyThrows
	public void read() {
		long id = 1L;
		mockMvc.perform(get(REST_URL + id +"/address"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(
						objectMapper.writeValueAsString(
								addressService.getAllActiveCustomerAddress(id))));
	}

	@Test
	@SneakyThrows
	public void create() {
		//Author created = new Author("TESTTEST");
		ResultActions action = mockMvc.perform(post("/api/customers/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(TestData.ADDRESS_NEW)));
		Address returned = objectMapper.readValue(
				action.andReturn().getResponse().getContentAsString(), Address.class);
		//created.setId(returned.getId());

		//assertE
		Assert.assertEquals(TestData.ADDRESS_NEW, returned);
	}

	@Test
	public void delete() {
	}
}