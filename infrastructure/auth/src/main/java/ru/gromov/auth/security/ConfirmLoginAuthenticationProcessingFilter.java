package ru.gromov.auth.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ru.gromov.auth.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Slf4j
public class ConfirmLoginAuthenticationProcessingFilter
        extends AbstractAuthenticationProcessingFilter {
    
    private final JwtProperties jwtProperties;
    
    @Autowired
    public ConfirmLoginAuthenticationProcessingFilter(JwtProperties jwtProperties) {
        super(new AntPathRequestMatcher("/auth/confirm", "POST"));
        this.jwtProperties = jwtProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("UUUUUUUUU");
        // 1. get the authentication header. Tokens are supposed to be passed in
        // the authentication header
        String header = request.getHeader(jwtProperties.getHeader());

        // 2. validate the header and check the prefix
        if (header == null || !header.startsWith(jwtProperties.getPrefix())) {
            log.info("In step 2, skip that filter");
            return null;
        }

        // If there is no token provided and hence the user won't be
        // authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a
        // token.

        // All secured paths that needs a token are already defined and secured
        // in config class.
        // And If user tried to access without access token, then he won't be
        // authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtProperties.getPrefix(), "");
        log.info("in step 3. get token: {}", token );
        try { // exceptions might be thrown in creating the claims if for
              // example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret().getBytes())
                    .parseClaimsJws(token).getBody();

            String username = claims.getSubject();
            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims
                        .get("authorities");

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used
                // by spring to represent the current authenticated / being
                // authenticated user.
                // It needs a list of authorities, which has type of
                // GrantedAuthority interface, where SimpleGrantedAuthority is
                // an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null,
                        authorities.stream().map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()));
                log.info("in step 5.  Create auth object: {}", auth);
                // 6. Authenticate the user
                // Now, user is authenticated
                log.info("in step 6.  Authenticate the user: {}", auth);
                return auth;
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't
            // be authenticated
            SecurityContextHolder.clearContext();
        }        

        return null;
    }
}
