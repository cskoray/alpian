package com.alpian.customerMapper.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({PARAMETER, METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CreatedAtDateValidator.class)
@Documented
public @interface ValidCreatedAtDate {

  String message() default "Can not be a future date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
