package org.aibles.book.exception;

import java.util.Map;

public class BaseExceptionRequest extends RuntimeException{

  private int statusException;
  private String code;
  private Map<String, Object> params;
  private Map<String, Object> errorMap;

  public int getStatusException() {
    return statusException;
  }

  public void setStatusException(int statusException) {
    this.statusException = statusException;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public void addParams(String key, Object value) {
    this.params.put(key, value);
  }

  public Map<String, Object> getErrorMap() {
    return errorMap;
  }

  public void setErrorMap(Map<String, Object> errorMap) {
    this.errorMap = errorMap;
  }

}
