package com.alpian.customerMapper.exception;

import static com.alpian.customerMapper.exception.ErrorType.INVALID_PARAMETER_ERROR;
import static com.alpian.customerMapper.exception.ErrorType.INVALID__DATE_PARAMETER_ERROR;
import static java.util.Collections.singletonList;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerMapperException.class)
  @ResponseBody
  public ResponseEntity<ErrorList> handleException(CustomerMapperException exception) {

    Error error = createError(exception);
    return new ResponseEntity<>(createErrorList(singletonList(error)),
        exception.getErrorType().getHttpStatus());
  }

  private Error createError(CustomerMapperException exception) {
    return createError(exception.getErrorType(), exception.getField());
  }

  @ExceptionHandler(DateTimeParseException.class)
  public ResponseEntity<ErrorList> handleValidationException(DateTimeParseException exception) {
    return createSingleErrorListResponse(INVALID__DATE_PARAMETER_ERROR, exception, "");
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorList> handleValidationException(ValidationException exception) {
    return createSingleErrorListResponse(INVALID_PARAMETER_ERROR, exception, "");
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ResponseEntity<ErrorList> handleValidationException(UnexpectedTypeException exception) {
    return createSingleErrorListResponse(INVALID_PARAMETER_ERROR, exception, "");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<ErrorList> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {

    ErrorType errorType = INVALID_PARAMETER_ERROR;
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    List<Error> errorList = fieldErrors.stream()
        .map(fieldError -> createError(errorType, fieldError.getField()))
        .collect(Collectors.toList());

    return new ResponseEntity<>(createErrorList(errorList), errorType.getHttpStatus());
  }

  private ResponseEntity<ErrorList> createSingleErrorListResponse(ErrorType errorType,
      Exception exception, String field) {

    Error error = createError(errorType, field);
    return new ResponseEntity<>(createErrorList(singletonList(error)), errorType.getHttpStatus());
  }

  private Error createError(ErrorType errorType, String field) {

    return Error.builder()
        .code(errorType.getCode())
        .description(errorType.getMessage())
        .field(field)
        .build();
  }

  private ErrorList createErrorList(List<Error> errorList) {
    return ErrorList.builder()
        .errors(errorList)
        .build();
  }
}
