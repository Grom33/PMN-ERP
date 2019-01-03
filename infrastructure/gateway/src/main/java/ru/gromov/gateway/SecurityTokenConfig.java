package ru.gromov.gateway;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtProperties jwtProperties;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
       http
        .cors().and()
    	.csrf().disable()
    	    // make sure we use stateless session; session won't be used to store user's state.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
        .and()
            // handle an authorized attempts 
            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> {
            		rsp.addHeader("Access-Control-Allow-Origin", "*");
            		
                	if (!HttpMethod.OPTIONS.name().equals(req.getMethod())) {
                    	rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                	}
            	}) 	
        .and()
           // Add a filter to validate the tokens with every request
           .addFilterAfter(new JwtTokenAuthenticationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class)
        // authorization requests config
        .authorizeRequests()
           // allow all who are accessing "auth" service
           .antMatchers(HttpMethod.POST, "/auth/**").permitAll()  
           .antMatchers(HttpMethod.OPTIONS, "/auth/**").permitAll()  
           .antMatchers(HttpMethod.OPTIONS, "/account/**").permitAll()
           .antMatchers(HttpMethod.OPTIONS, "/library/**").permitAll()
           .antMatchers(HttpMethod.POST, "/account").permitAll()
           .antMatchers("/account/current").hasAuthority("AUTHENTICATED")
           .antMatchers("/account/**").hasAuthority("AUTHENTICATED_ADMIN")
           .antMatchers("/library/**").hasAuthority("AUTHENTICATED")
           .antMatchers("/actuator/**").permitAll()
           .antMatchers("/hystrix/**").permitAll()
           .antMatchers("/webjars/**").permitAll()
           .antMatchers("/proxy.stream/**").permitAll()
           // Any other request must be authenticated
           .anyRequest().authenticated(); 
		// @formatter:on
	}
}

