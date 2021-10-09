package de.cadentem.advice;

import de.cadentem.exceptions.ArtistNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArtistNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ArtistNotFoundException.class)
    public String artistNotFoundHandler(final ArtistNotFoundException exception) {
        return exception.getMessage();
    }
}
