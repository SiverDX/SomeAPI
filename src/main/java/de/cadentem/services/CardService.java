package de.cadentem.services;

import de.cadentem.entities.Card;
import de.cadentem.repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

//    public Page<Artist> getAll(final Pageable pageable) {
//        return repository.findAll(pageable);
//    }

    public List<Card> getAll() {
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
