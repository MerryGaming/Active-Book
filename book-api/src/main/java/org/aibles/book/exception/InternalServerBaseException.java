package org.aibles.book.exception;

import org.springframework.http.HttpStatus;


public class InternalServerBaseException extends BaseExceptionRequest {
    public InternalServerBaseException() {
        setStatusException(HttpStatus.NOT_FOUND.value());
        setCode("org.aibles.book.exception.InternalServerBaseException");
    }

}
