package de.cadentem.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(final Long id) {
        super("Could not find artist <" + id + ">");
    }
}
