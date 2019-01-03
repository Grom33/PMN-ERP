package ru.gromov.gateway;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProperties jwtProperties;

	@Autowired
	public JwtTokenAuthenticationFilter(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	@SuppressWarnings("Duplicates")
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	        HttpServletResponse response, FilterChain chain)
	        throws ServletException, IOException {

		String header = request.getHeader(jwtProperties.getHeader());

		log.info("{}: {} - {}", request.getMethod(), request.getRequestURI(), header);

		if (header == null || !header.startsWith(jwtProperties.getPrefix())) {
			chain.doFilter(request, response); // If not valid, go to the next
			                                   // filter.
			log.info("In step 2, skip that filter");
			return;
		}

		String token = header.replace(jwtProperties.getPrefix(), "");

		log.info("in step 3. get token: {}", token );
		try {
			log.info("in step 4.try to validate token: {}", token );
			Claims claims = Jwts.parser()
			        .setSigningKey(jwtProperties.getSecret().getBytes())
			        .parseClaimsJws(token).getBody();

			String username = claims.getSubject();
				log.info("in step 4. username is: {}", username);
			if (username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims
				        .get("authorities");

				log.info("in step 4. authorities of username is: {}", authorities);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				        username, null,
				        authorities.stream().map(SimpleGrantedAuthority::new)
				                .collect(Collectors.toList()));
				log.info("in step 5.  Create auth object: {}", auth);

				SecurityContextHolder.getContext().setAuthentication(auth);
				log.info("in step 6.  Authenticate the user: {}", auth);

			}

		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		chain.doFilter(request, response);
		log.info("go to next filter");
	}
}
