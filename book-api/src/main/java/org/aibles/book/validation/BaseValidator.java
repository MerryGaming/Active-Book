package org.aibles.book.validation;


import java.util.HashMap;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book.exception.BadRequestBaseException;
import org.aibles.book.exception.NotFoundBaseException;


@Slf4j
public class BaseValidator<T> {

  private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
      .getValidator();

  public void validate() {
    var errorMap = new HashMap<String, Object>();

    var violations = VALIDATOR.validate(this);
    if (!violations.isEmpty()) {
      log.info("(validate){} --> INVALID", this.getClass().getTypeName());
      for (var violation : violations) {
        errorMap.put(getField(violation), violation.getMessage());
      }
    }

    if (!errorMap.isEmpty()) {
      System.out.println(errorMap);
      throw new BadRequestBaseException(errorMap);
    }
  }

  public void validateClient() {
    var errorMap = new HashMap<String, Object>();

    var violations = VALIDATOR.validate(this);
    if (!violations.isEmpty()) {
      log.info("(validateClient){} --> INVALID", this.getClass().getTypeName());
      for (var violation : violations) {
        errorMap.put(getField(violation), violation.getMessage());
      }
    }

    if (!errorMap.isEmpty()) {
      System.out.println(errorMap);
      throw new NotFoundBaseException(errorMap);
    }
  }

  public Map<String, Object> isValid() {
    var violations = VALIDATOR.validate(this);
    if (!violations.isEmpty()) {
      log.info("(isValid){} --> INVALID", this.getClass().getTypeName());
      for (var violation : violations) {
        log.error("(isValid){}: {}", getField(violation), violation.getMessage());
      }
    }
    return new HashMap<>();
  }

  private String getField(ConstraintViolation<BaseValidator<T>> violation) {
    return violation.getPropertyPath().toString();
  }


}
