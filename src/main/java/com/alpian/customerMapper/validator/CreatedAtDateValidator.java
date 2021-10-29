package com.alpian.customerMapper.validator;

import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static java.time.ZoneId.of;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreatedAtDateValidator implements ConstraintValidator<ValidCreatedAtDate, String> {

  @Override
  public void initialize(ValidCreatedAtDate constraintAnnotation) {
  }

  @Override
  public boolean isValid(String createdAt, ConstraintValidatorContext context) {
    return (validateCreatedAtDate(createdAt));
  }

  private boolean validateCreatedAtDate(String createdAt) throws DateTimeParseException {

    LocalDate parse = parse(createdAt, ofPattern("yyyy-MM-dd"));
    return parse.isBefore(now(of("Europe/London")));
  }
}
