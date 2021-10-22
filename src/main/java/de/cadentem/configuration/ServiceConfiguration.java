package de.cadentem.configuration;

import de.cadentem.services.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        };
    }
//
//    private void logInfo(final JpaRepository<?, ?>... repositories) {
//        Arrays.stream(repositories).forEach(repository -> repository.findAll().forEach(entity -> LOG.info("Preloaded " + entity)));
//    }
}
