package de.cadentem.advice;

import de.cadentem.exceptions.CardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CardNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardNotFoundException.class)
    public String cardNotFoundHandler(final CardNotFoundException exception) {
        return exception.getMessage();
    }
}
