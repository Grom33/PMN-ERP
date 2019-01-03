package ru.gromov.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.gromov.auth.domain.AccountDetails;

@Service
public class LibraryUserDetailsService implements UserDetailsService {
    private AccountService accountService;

    @Autowired
    public LibraryUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return accountService.findByNameOrEmail(username).map(account -> {
            String[] roles = Optional.ofNullable(account.getRoles())
                    .map(rls -> rls.split(",")).orElse(new String[0]);

            UserDetails userDetails = User.withUsername(username)
                    .password(account.getPassword()).roles(roles).build();
            
            return new AccountDetails(account.getId(),
                    userDetails.getUsername(), userDetails.getPassword(),
                    userDetails.getAuthorities());
        }).orElseThrow(() -> new UsernameNotFoundException(
                "User not found: " + username));
    }
}
