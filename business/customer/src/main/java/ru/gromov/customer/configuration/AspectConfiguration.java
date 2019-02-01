package ru.gromov.customer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import ru.gromov.customer.aop.logging.Logging;
import ru.gromov.customer.util.Constants;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {
    @Bean
    @Profile(Constants.PROFILE_DEVELOPMENT)
    public Logging loggingAspect(Environment env) {
        return new Logging(env);
    }
}
