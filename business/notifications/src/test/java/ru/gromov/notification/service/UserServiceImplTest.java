package ru.gromov.notification.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.User;
//import ru.gromov.notification.service.security.UserService;

import java.util.Collections;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@Transactional
@WithUserDetails("Ivan")
public class UserServiceImplTest {
	User expectedUser = new User();

	//@Autowired
	//private UserService userService;

	{
		expectedUser.setId(11L);
		expectedUser.setUsername("Ivan111");

	}

	/*@Test
	//@WithMockUser(username = "John", roles = "ADMIN", authorities = "ADMIN")
	public void save() {
		userService.save(expectedUser);
	}*/

	/*@Test
	//@WithMockUser(username = "John", roles = "ADMIN", authorities = "ADMIN")
	public void findByUsername() {
		userService.save(expectedUser);
		User actualUser = userService.findByUsername(expectedUser.getUsername());
		Assert.assertEquals(expectedUser, actualUser);
	}*/

	/*@Test(expected = UserNotFoundException.class)
	//@WithMockUser(username = "John", roles = "ADMIN", authorities = "ADMIN")
	public void delete() {
		userService.save(expectedUser);
		User actualUser = userService.findByUsername(expectedUser.getUsername());
		Assert.assertEquals(expectedUser, actualUser);
		userService.delete(actualUser.getId());
		userService.findByUsername(expectedUser.getUsername());
	}*/
}
