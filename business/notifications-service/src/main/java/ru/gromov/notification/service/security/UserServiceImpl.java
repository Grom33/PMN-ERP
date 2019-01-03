package ru.gromov.notification.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.domain.provider.EmailExternalServiceAccount;
import ru.gromov.notification.repository.UserRepository;
import ru.gromov.notification.util.exception.UserNotFoundException;


import java.util.List;


/**
 * Service for working with user
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username)
				.orElseGet(()->save(new User(username)));
	}

	@Secured("ROLE_ADMIN")
	@Override
	public void delete(Long id) {
		userRepository.delete(
				userRepository.findById(id)
						.orElseThrow(() -> new UserNotFoundException(
								String.format("User with id %s not found!", id))));
	}

	@Secured("ROLE_ADMIN")
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void addAccountToUser(Long id, EmailExternalServiceAccount account) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(
						String.format("User with id %s not found!", id)));
		user.getEmailExternalServiceAccounts().add(account);
		userRepository.save(user);
	}

}
