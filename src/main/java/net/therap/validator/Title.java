package net.therap.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CourseTitleValidator.class)
public @interface Title {

    String message() default "Title already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
