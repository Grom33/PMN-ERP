package ru.gromov.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableZuulProxy // Enable Zuul
@EnableEurekaClient // It acts as a eureka client
@EnableHystrixDashboard
@SpringBootApplication
public class GatewayApp implements CommandLineRunner {

    @Autowired
    private JwtProperties jwtProperties;

    public static void main(String[] args) throws Exception {
		SpringApplication.run(GatewayApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
        log.info("Library properties: ");
        log.info("{} = {}", "librari.jwt.header", jwtProperties.getHeader());
        log.info("{} = {}", "librari.jwt.prefix", jwtProperties.getPrefix());
        log.info("{} = {}", "librari.jwt.expiration",
                jwtProperties.getExpiration());
	}
}
