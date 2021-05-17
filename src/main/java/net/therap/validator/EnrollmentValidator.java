package net.therap.validator;

import net.therap.dao.CourseEnrollmentDao;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
public class EnrollmentValidator {
    CourseEnrollmentDao courseEnrollmentDao;

    public EnrollmentValidator() {
        this.courseEnrollmentDao = new CourseEnrollmentDao();
    }
}
