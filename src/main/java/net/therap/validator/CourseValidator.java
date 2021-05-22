package net.therap.validator;

import net.therap.dao.CourseDao;
import net.therap.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Component
@Qualifier("courseValidator")
public class CourseValidator implements Validator {

    @Autowired
    private CourseDao courseDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Course.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Course course = (Course) target;
        if (courseDao.isTitleExists(course.getTitle()) != 0) {
            errors.rejectValue("title", "title.exist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.required");
    }
}
