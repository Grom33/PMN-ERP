package ru.gromov.notification.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProperties jwtProperties;

	@Autowired
	public JwtTokenAuthenticationFilter(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		log.info("JwtTokenAuthenticationFilter");
		String header = request.getHeader(jwtProperties.getHeader());
		if (header == null || !header.startsWith(jwtProperties.getPrefix())) {
			chain.doFilter(request, response);
			log.info("In step 2, skip that filter");
			return;
		}
		String token = header.replace(jwtProperties.getPrefix(), "");
		log.info("Token {}", token );
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(jwtProperties.getSecret().getBytes())
					.parseClaimsJws(token).getBody();
			String username = claims.getSubject();
			log.info("UserName  {}", username );
			if (username != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims
						.get("authorities");
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
						username, null,
						authorities.stream().map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList()));
				SecurityContextHolder.getContext().setAuthentication(auth);
				log.info("in step 6.  Authenticate the user: {}", auth);
			}
		} catch (Exception e) {

			SecurityContextHolder.clearContext();
			log.info("We have some problem!");
			log.info("{}",e);
		}
		chain.doFilter(request, response);
		log.info("Next!");
	}
}
