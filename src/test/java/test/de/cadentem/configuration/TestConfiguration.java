package test.de.cadentem.configuration;

import de.cadentem.services.CardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public CardService cardService() {
        return new CardService(null);
    }
}
