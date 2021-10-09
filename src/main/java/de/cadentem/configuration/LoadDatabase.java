package de.cadentem.configuration;

import de.cadentem.Status;
import de.cadentem.entities.Artist;
import de.cadentem.entities.Order;
import de.cadentem.repositories.ArtistRepository;
import de.cadentem.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class LoadDatabase {
    private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(final ArtistRepository artistRepository, final OrderRepository orderRepository) {
        return args -> {
            artistRepository.save(new Artist("Some Weird Dude", LocalDateTime.now()));
            artistRepository.save(new Artist("Another Weird Dude", LocalDateTime.now()));

            orderRepository.save(new Order("Some CD", Status.IN_PROGRESS));
            orderRepository.save(new Order("Deluxe Box Vol. 3", Status.CANCELLED));

            logInfo(artistRepository, orderRepository);
        };
    }

    private void logInfo(final JpaRepository<?, ?>... repositories) {
        Arrays.stream(repositories).forEach(repository -> repository.findAll().forEach(entity -> LOG.info("Preloaded " + entity)));
    }
}
