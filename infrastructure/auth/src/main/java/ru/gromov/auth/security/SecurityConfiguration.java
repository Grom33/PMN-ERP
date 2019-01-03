package ru.gromov.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ru.gromov.auth.config.JwtProperties;
import ru.gromov.auth.service.LibraryUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private LibraryUserDetailsService libraryUserDetailsService;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http
		    .cors().and()
	    	.csrf().disable()
	    	// make sure we use stateless session; session won't be used to store user's state.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterAfter(new JwtTokenAuthenticationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class)
	    .authorizeRequests()
    	    // allow all POST requests 
    	    .antMatchers("/auth/login").permitAll()
            .antMatchers("/auth/confirm").authenticated()
            .antMatchers(HttpMethod.POST, "/account").permitAll()
            .antMatchers("/account/current").hasAuthority(Authorities.AUTHENTICATED)
            .antMatchers("/account/**").hasAuthority(Authorities.AUTHENTICATED + "_" + Roles.ADMIN)
    	    .antMatchers("/actuator/**").permitAll()
    	    // any other requests must be authenticated
    	    .anyRequest().authenticated();
		// @formatter:on
    }

    @Override
    public UserDetailsService userDetailsService() {
        return libraryUserDetailsService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
