package de.cadentem.exceptions;

public class WrongParameterException extends RuntimeException {
    public WrongParameterException(final String parameter) {
        super("Request parameter was not valid - a parameter named <" + parameter + "> was expected.");
    }
}
