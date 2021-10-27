package de.cadentem.utils;

import de.cadentem.entities.Card;
import de.cadentem.entities.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityCreator {
    public static List<Rarity> createRarities(final int amount) {
        List<Rarity> rarities = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Rarity rarity = createRarity(i, UUID.randomUUID().toString());
            rarities.add(rarity);
        }

        return rarities;
    }

    public static List<Rarity> createRarities(final String value) {
        Rarity rarity = createRarity(0, value);

        List<Rarity> rarities = new ArrayList<>();
        rarities.add(rarity);

        return rarities;
    }

    public static Rarity createRarity(final long id, final String value) {
        // todo :: set random values if empty? some other way to do this?
        Rarity rarity = new Rarity(value);
        rarity.setId(id);

        return rarity;
    }

    public static List<Card> createCards(final int amount) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Card card = createCard(i, UUID.randomUUID().toString());
            cards.add(card);
        }

        return cards;
    }

    private static Card createCard(final int id, final String name) {
        Card card = new Card(name);
        card.setId(id);

        return card;
    }
}
