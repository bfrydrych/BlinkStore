package com.blinkstore.controllers.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnoughItemsOnStockValidator.class)
public @interface EnoughItemsOnStock {
	String message() default "{validator.enoughItemsOnStock}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
