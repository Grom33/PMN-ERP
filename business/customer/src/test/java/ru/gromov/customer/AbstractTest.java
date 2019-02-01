package ru.gromov.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.customer.util.Constants;
import ru.gromov.customer.util.TestUtil;

import javax.annotation.PostConstruct;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

@SpringBootTest(classes = CustomerApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Transactional
@Profile(Constants.PROFILE_TEST)
public abstract class AbstractTest {
	protected ObjectMapper objectMapper = new ObjectMapper();
	protected TestUtil util = new TestUtil();

	@PostConstruct
	private void postConstruct() {
		objectMapper.registerModule(new JavaTimeModule());
	}
}
