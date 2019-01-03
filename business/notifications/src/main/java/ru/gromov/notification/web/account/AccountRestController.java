package ru.gromov.notification.web.account;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gromov.notification.domain.provider.EmailExternalServiceAccount;
import ru.gromov.notification.domain.User;
//import ru.gromov.notification.service.security.SecurityService;
//import ru.gromov.notification.service.security.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountRestController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	//@Autowired
	//private SecurityService securityService;

	//@Autowired
	//private UserService userService;

/*	@GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		log.debug("GET request to get all users");
		return userService.getAll();
	}*/

	/*@PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addUser(@RequestBody User user) {
		log.debug("POST request to add new user, with message payload:{} ", user);
		//return userService.save(user).getUsername();
		System.out.println(user);
		userService.save(user);
		return "OK";
	}*/

	/*@PutMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUser(@RequestBody User user) {
		log.debug("PUT request to update user, with message payload:{} ", user);
		return userService.save(user).getUsername();
	}*/

	/*@DeleteMapping(value = "/api/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		log.debug("DELETE request to delete user, with id:{} ", id);
		userService.delete(id);
	}*/

	/*@PostMapping(value = "/api/users/{id}/accounts",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addAccountToUser(@PathVariable Long id,
	                             @RequestBody EmailExternalServiceAccount account) {
		log.debug("POST request to add account to user with id:{}", id);
		userService.addAccountToUser(id, account);
	}*/
}
