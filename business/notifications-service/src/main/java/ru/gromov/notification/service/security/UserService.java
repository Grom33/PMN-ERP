package ru.gromov.notification.service.security;



import ru.gromov.notification.domain.User;
import ru.gromov.notification.domain.provider.EmailExternalServiceAccount;

import java.util.List;

public interface UserService {

	User save(User user);

	User findByUsername(String username);

	void delete(Long id);

	List<User> getAll();

	void addAccountToUser(Long id, EmailExternalServiceAccount account);
}
