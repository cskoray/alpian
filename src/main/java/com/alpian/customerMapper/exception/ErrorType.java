package com.alpian.customerMapper.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

  INVALID_PARAMETER_ERROR("10000", "Invalid field(s). ", BAD_REQUEST),
  INVALID__DATE_PARAMETER_ERROR("10001", "Invalid field(s). ", BAD_REQUEST),
  NO_CUSTOMER_FOUND_ERROR("10002", "No customer found. ", NOT_FOUND);

  private String code;
  private String message;
  private HttpStatus httpStatus;
}
