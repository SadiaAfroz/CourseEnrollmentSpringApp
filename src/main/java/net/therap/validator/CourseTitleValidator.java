package net.therap.validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CourseTitleValidator implements ConstraintValidator<Title, String> {

    @Autowired
    private CourseValidator courseValidator;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (courseValidator.isValidTitle(value)) {
            return true; //pass
        }
        return false; //fail
    }
}
