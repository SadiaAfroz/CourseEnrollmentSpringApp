package net.therap.service;

import net.therap.dao.CourseEnrollmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Service
public class CourseEnrollmentService {

    @Autowired
    CourseEnrollmentDao ced;

    public void enrollTrainees(int courseId, int traineeId) {
        ced.addTraineeList(courseId, traineeId);
    }

    public void removeTrainee(int courseId, int traineeId) {
        ced.removeTrainee(courseId, traineeId);
    }
}
