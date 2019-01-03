package ru.gromov.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		        .allowedMethods(RequestMethod.OPTIONS.name(),
		                RequestMethod.GET.name(), RequestMethod.POST.name(),
		                RequestMethod.PUT.name(), RequestMethod.DELETE.name());
	}
}
