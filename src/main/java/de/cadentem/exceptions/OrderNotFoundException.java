package de.cadentem.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(final Long id) {
        super("Could not find order <" + id + ">");
    }
}
