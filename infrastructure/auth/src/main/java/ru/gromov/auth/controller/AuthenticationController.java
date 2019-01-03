package ru.gromov.auth.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import ru.gromov.auth.config.JwtProperties;
import ru.gromov.auth.domain.AccountDetails;
import ru.gromov.auth.domain.UserCredentials;
import ru.gromov.auth.security.Authorities;
import ru.gromov.auth.security.Roles;
import ru.gromov.auth.service.AccountService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(
			@RequestBody UserCredentials userCredentials) {
		log.info("UserCredentials: " + userCredentials.getUsername()+" | "+userCredentials.getPassword());
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userCredentials.getUsername(), userCredentials.getPassword());
		Authentication auth = authenticationManager
				.authenticate(authenticationToken);
		if (auth.getPrincipal() instanceof AccountDetails) {
			accountService.updateVerification(
					((AccountDetails) auth.getPrincipal()).getId());
		}
		return createResponse(auth, false);
	}

	@GetMapping("/test")
	public String hello(){
		return "TETSTSTS";
	}

	@PostMapping("/confirm")
	public ResponseEntity<?> authenticateUser(Authentication authentication,
	                                          @RequestParam("code") String code) {
		log.info("UserCredentials: " + authentication.getCredentials());

		if (accountService.checkAccount(authentication.getName(), code)) {
			return createResponse(authentication, true);
		} else {
			return new ResponseEntity<>(new ApiResponse(false, "invalid code"),
					HttpStatus.FORBIDDEN);
		}
	}

	private ResponseEntity<?> createResponse(Authentication authentication,
	                                         boolean confirmed) {
		Long now = System.currentTimeMillis();
		List<String> authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		if (confirmed && !authorities.contains(Authorities.AUTHENTICATED)) {
			authorities.add(Authorities.AUTHENTICATED);

			String authenticatedAdmin = Authorities.AUTHENTICATED + "_"
					+ Roles.ADMIN;

			if (authorities.contains("ROLE_" + Roles.ADMIN)
					&& !authorities.contains(authenticatedAdmin)) {
				authorities.add(authenticatedAdmin);
			}
		}

		String token = Jwts.builder().setSubject(authentication.getName())
				// Convert to list of strings.
				// This is important because it affects the way we get them back
				// in the Gateway.
				.claim("authorities", authorities).setIssuedAt(new Date(now))
				.setExpiration(
						new Date(now + jwtProperties.getExpiration() * 1000)) // in
				// milliseconds
				.signWith(SignatureAlgorithm.HS512,
						jwtProperties.getSecret().getBytes())
				.compact();

		// Add token to header
		HttpHeaders responseHeaders = new HttpHeaders();

		log.info("Send token to: " + authentication.getPrincipal() + " = "
				+ token);

		responseHeaders.add(jwtProperties.getHeader(),
				jwtProperties.getPrefix() + token);
		responseHeaders.add("Access-Control-Expose-Headers",
				jwtProperties.getHeader());

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
	}
}
