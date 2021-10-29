package com.alpian.customerMapper.exception;

import lombok.Getter;

@Getter
public class CustomerMapperException extends RuntimeException {

  private final ErrorType errorType;
  private String field;
  private String code;

  public CustomerMapperException(ErrorType errorType) {
    super(errorType.getMessage());
    this.errorType = errorType;
  }

  public CustomerMapperException(ErrorType errorType, String field) {
    super(errorType.getMessage());
    this.code = errorType.getCode();
    this.errorType = errorType;
    this.field = field;
  }

}
