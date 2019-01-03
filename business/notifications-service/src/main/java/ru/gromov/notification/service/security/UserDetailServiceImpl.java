package ru.gromov.notification.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.User;
import ru.gromov.notification.repository.UserRepository;
import ru.gromov.notification.util.exception.UserNotFoundException;


import java.util.HashSet;
import java.util.Set;

/*
 * Created by Gromov Vitaly (Grom33), 2018
 * e-mail: mr.gromov.vitaly@gmail.com
 */

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
