package com.backend.digitalbank.view.service.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.backend.digitalbank.view.service.validator.validation.SingleEmailConstraintValidator;

@Documented
@Constraint(validatedBy = SingleEmailConstraintValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateSingleEmail {

	String message() default "Email já existe.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
