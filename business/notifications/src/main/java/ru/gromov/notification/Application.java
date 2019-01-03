package ru.gromov.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
@EnableScheduling
@EnableIntegration

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
