package ru.gromov.notification.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootController {

	@GetMapping("/public")
	public String users(Authentication authentication) {
		return "HELLO detail"+authentication.getDetails()+"; principal " + authentication.getPrincipal()
				+"; credential " + authentication.getCredentials() +"; author " + authentication.getAuthorities()
				+"; name " + authentication.getName();
	}
}
