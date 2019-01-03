package ru.gromov.notification.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.service.message.MailMessageService;
//import ru.gromov.notification.service.security.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@WithMockUser(roles = {"ADMIN"})
public class MailMessageMessageServiceImplTest {
	@Autowired
	private MailMessageService mailMessageService;

	//@Autowired
	//private UserService userService;

	private MailMessage expectedMailMessage = new MailMessage();
	private User user = new User();


	{
		user.setUsername("Ivan");

		expectedMailMessage.setDateTimeCreated(LocalDateTime.now());
		expectedMailMessage.setMessage("TEST!!!!!!");
		expectedMailMessage.setStatus(MessageStatus.CREATED);
		expectedMailMessage.setUuid(UUID.randomUUID());
		expectedMailMessage.setUser(user);
	}

	@Before
	public void setUp() {
		//userService.save(user);
		mailMessageService.save(expectedMailMessage);
	}

	@Test
	public void getCount() {
		System.out.println(mailMessageService.count());
	}

	@Test
	public void save() {
		System.out.println(mailMessageService.save(expectedMailMessage));
	}

	@Test(expected = AccessDeniedException.class)
	public void getById() {
		mailMessageService.getById(100000L);
	}

	@Test
	public void getAll() {
		mailMessageService.getAll();
	}

	@Test
	public void delete() {
		mailMessageService.delete(100000L);
	}
}