package de.cadentem.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(final Long id) {
        super("Could not find artist <" + id + ">");
    }
}
