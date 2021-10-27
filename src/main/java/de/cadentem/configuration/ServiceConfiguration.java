package de.cadentem.configuration;

import de.cadentem.security.SecurityRole;
import de.cadentem.services.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceConfiguration.class);

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader("X-Api-Key", System.getenv("pokemontcg.api.key"))
                .baseUrl("https://api.pokemontcg.io/v2/")
                .build();
    }

    @Bean
    public CommandLineRunner initDatabase(final InitService service) {
        return args -> {
            service.initRarities();
            service.initSuperTypes();
            service.initSubTypes();
            service.initTypes();

            LOG.info("Finished initialization");
        };
    }
}
