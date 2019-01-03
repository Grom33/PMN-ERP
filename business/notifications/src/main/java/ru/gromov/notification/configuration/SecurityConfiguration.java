package ru.gromov.notification.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import ru.gromov.notification.service.security.JwtProperties;
import ru.gromov.notification.service.security.JwtTokenAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtProperties jwtProperties;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(new JwtTokenAuthenticationFilter(jwtProperties), FilterSecurityInterceptor.class)
				.authorizeRequests()
				.anyRequest().authenticated()
		;
	}
}
