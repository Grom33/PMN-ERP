package ru.gromov.notification.service.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class JwtProperties {

	@Value("${library.jwt.header}")
	private String header;

	@Value("${library.jwt.prefix}")
	private String prefix;

	@Value("${library.jwt.expiration}")
	private int expiration;

	@Value("${library.jwt.secret}")
	private String secret;
}
