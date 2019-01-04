package ru.gromov.customer.web;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import ru.gromov.customer.TestData;
import ru.gromov.customer.domain.Address;
import ru.gromov.customer.service.AddressService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

public class AddressRestControllerTest extends AbstractWebTest {

	@Autowired
	private AddressService addressService;

	private static final String REST_URL = "/api/customers/";

	@Test
	@SneakyThrows
	public void read() {
		long id = 1L;
		mockMvc.perform(get(REST_URL + id + "/address"))
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

		ResultActions action = mockMvc.perform(post("/api/customers/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(TestData.ADDRESS_NEW)));
		Address returned = objectMapper.readValue(
				action.andReturn().getResponse().getContentAsString(), Address.class);
		assertThat(TestData.ADDRESS_NEW).isEqualToIgnoringGivenFields(returned, "id", "createdAt", "lastModified");

	}

	@Test
	public void delete() {
	}
}