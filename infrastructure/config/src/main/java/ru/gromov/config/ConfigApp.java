package ru.gromov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApp implements CommandLineRunner {

    @Autowired
    private LibraryProperties libraryProperties;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Library properties: ");
        log.info("{} = {}", "librari.auth.url",
                libraryProperties.getAuth().getUrl());
        log.info("{} = {}", "librari.jwt.header",
                libraryProperties.getJwt().getHeader());
        log.info("{} = {}", "librari.jwt.prefix",
                libraryProperties.getJwt().getPrefix());
        log.info("{} = {}", "librari.jwt.expiration",
                libraryProperties.getJwt().getExpiration());
    }
}
