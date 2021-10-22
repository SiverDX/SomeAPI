package de.cadentem.services;

import de.cadentem.entities.Card;
import de.cadentem.repositories.CardRepository;
import de.cadentem.repositories.RarityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    // todo :: own service?
    private final RarityRepository rarityRepository;
    private WebClient webClient;

//    public Page<Artist> getAll(final Pageable pageable) {
//        return repository.findAll(pageable);
//    }

    public Iterable<Card> getAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> find(final Long id) {
        return cardRepository.findById(id);
    }

    public Card save(final Card artist) {
        return cardRepository.save(artist);
    }

    public void delete(final Long id) {
        cardRepository.deleteById(id);
    }
}
