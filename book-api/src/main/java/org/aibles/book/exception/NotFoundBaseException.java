package org.aibles.book.exception;

import org.springframework.http.HttpStatus;


public class NotFoundBaseException extends BaseExceptionRequest {
    public NotFoundBaseException(Object variable) {
        setStatusException(HttpStatus.NOT_FOUND.value());
        setCode("org.aibles.book.exception.NotFoundBaseException");
        addParams("variable", variable);

    }
}
