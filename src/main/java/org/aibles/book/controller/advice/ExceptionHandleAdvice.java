package org.aibles.book.controller.advice;


import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book.exception.BaseExceptionRequest;
import org.aibles.book.exception.response.ExceptionResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

  private final MessageSource messageSource;

  public ExceptionHandleAdvice(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(BaseExceptionRequest.class)
  public ResponseEntity<Map<String, Object>> handle(
      BaseExceptionRequest ex,
      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
    log.info("(handle)exception : {}, locale: {}", ex.getCode(), locale);
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("status", ex.getStatusException());
    errorMap.put("code", ex.getCode());
    if (ex.getErrorMap() != null) {
      ex.getErrorMap()
          .forEach((key, value) -> errorMap.put(
              key,
              messageSource.getMessage(
                  Objects.requireNonNull(value.toString()), null, locale)));
    }
    if (ex.getParams() != null) {
      errorMap.putAll(ex.getParams());
    }
    return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusException())).body(errorMap);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handle(
      MethodArgumentNotValidException ex,
      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
    log.info("(handle)exception: {}, locale : {}", ex.getClass().getName(), locale);
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error ->
                errorMap.put(
                    error.getField(),
                    messageSource.getMessage(
                        Objects.requireNonNull(error.getDefaultMessage()), null, locale)));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
  }


}

