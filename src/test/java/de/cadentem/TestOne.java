package de.cadentem;

import de.cadentem.controllers.CardController;
import de.cadentem.entities.Card;
import de.cadentem.services.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class TestOne {
    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(cardService.getAll()).thenReturn(createCards());
    }

    @Test
    public void testGetAll() {
        ResponseEntity<List<Card>> response = cardController.getAll();

        List<Card> cards = response.getBody();

        Assert.notNull(cards, "The list must not be null");
        Assertions.assertEquals(10, cards.size());
    }

    @Test
    public void testFindByName() {
        // ...
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            cards.add(createCard());
        }

        return cards;
    }

    private Card createCard() {
        Card card = new Card();
        card.setName(UUID.randomUUID().toString());

        return card;
    }
}
