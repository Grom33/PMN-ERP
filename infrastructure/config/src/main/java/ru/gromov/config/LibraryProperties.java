package ru.gromov.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "library")
public class LibraryProperties {
    @Getter
    @Setter
    public static class Auth {
        private String url;
        private String defaultEmail;
    }

    @Getter
    @Setter
    public static class Jwt {
        private String header;
        private String prefix;
        private int expiration;
        private String secret;
    }

    private Auth auth;
    private Jwt jwt;
}
