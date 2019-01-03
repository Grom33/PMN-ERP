package ru.gromov.notification.service;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.service.message.MailMessageService;
//import ru.gromov.notification.service.security.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MailMessageMessageServiceSecurityTest {
	@Autowired
	private MailMessageService mailMessageService;

	//@Autowired
	//private UserService userService;

	private MailMessage expectedMailMessage = new MailMessage();
	private User user = new User();


	{
/*		user.setUsername("Ivan");
		user.setPassword("password");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setEnabled(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setRoles(Collections.singleton(new Role("USER")));*/

		expectedMailMessage.setDateTimeCreated(LocalDateTime.now());
		expectedMailMessage.setMessage("TEST!!!!!!");
		expectedMailMessage.setStatus(MessageStatus.CREATED);
		expectedMailMessage.setUuid(UUID.randomUUID());
	//	expectedMailMessage.setUser(user);
	}
	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void getCountByAnonymous() {
		mailMessageService.count();
	}

	@Test
	@WithMockUser(username = "john", roles = {"ADMIN"})
	public void getCountWithAdminRole() {
		mailMessageService.count();
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "john", roles = {"VIEWER"})
	public void getCountWithDeniedRole() {
		mailMessageService.count();
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void saveWithAnonymous() {
		mailMessageService.save(expectedMailMessage);
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "john", roles = {"VIEWER"})
	public void saveWithDeniedRole() {
		mailMessageService.save(expectedMailMessage);
	}

	@Test
	@WithMockUser(username = "john", roles = {"ADMIN"})
	public void saveWithAdminRole() {
		mailMessageService.save(expectedMailMessage);
	}

	@Test
	@WithMockUser(username = "john")
	public void saveWithUserRole() {
		mailMessageService.save(expectedMailMessage);
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void getByIdWithAnonym() {
		mailMessageService.getById(1L);
	}

	@Test
	@WithMockUser(username = "john")
	public void getByIdWithUserRole() {
		mailMessageService.getById(2L);
	}

	@Test
	@WithMockUser(username = "john", roles = {"ADMIN"})
	public void getByIdWithAdminRole() {
		mailMessageService.getById(2L);
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void getAllWithAnonymous() {
		mailMessageService.getAll();
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "john")
	public void getAllWithUserRole() {
		mailMessageService.getAll();
	}

	@Test(expected = AccessDeniedException.class)
	@WithAnonymousUser
	public void deleteWithAnonymousRole() {
		mailMessageService.delete(2L);
	}

	@Test(expected = AccessDeniedException.class)
	@WithMockUser(username = "john")
	public void deleteWithUserRole() {
		mailMessageService.delete(2L);
	}

	@Test
	@WithMockUser(username = "john", roles = {"ADMIN"})
	public void deleteWithAdminRole() {
		mailMessageService.delete(2L);
	}
}
