package ru.gromov.auth.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class AccountDetails extends User implements UserDetails {

    private final String id;

    public AccountDetails(String id, String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        
        this.id = id;
    }
}
