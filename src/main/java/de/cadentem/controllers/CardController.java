package de.cadentem.controllers;

import de.cadentem.entities.Card;
import de.cadentem.exceptions.CardNotFoundException;
import de.cadentem.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private final CardService service;

    public CardController(final CardService service) {
        this.service = service;
    }

//    @GetMapping
//    public Page<Artist> all(final Pageable pageable) {
//        return service.getAll(pageable);
//    }

    @GetMapping
    public ResponseEntity<List<Card>> getAll() {
        List<Card> cards = service.getAll();

        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> find(@PathVariable final Long id) throws CardNotFoundException {
        Card artist = service.find(id).orElseThrow(() -> new CardNotFoundException(id));

        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody final Card newArtist) {
        Card artist = service.save(newArtist);

        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
