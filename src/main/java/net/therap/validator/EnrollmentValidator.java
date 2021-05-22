package net.therap.validator;

import net.therap.dao.CourseEnrollmentDao;
import net.therap.dao.TraineeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static net.therap.util.Capacity.MAX_TRAINEE_TO_COURSE_ENROLL;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Component
public class EnrollmentValidator {

    @Autowired
    private TraineeDao traineeDao;

    public boolean hasTraineeCapacity(int courseId, int numberOfTrainees) {
        int countTrainees = traineeDao.findAllByCourseId(courseId).size();
        if ((countTrainees + numberOfTrainees) <= MAX_TRAINEE_TO_COURSE_ENROLL) {
            return true;
        }
        return false;
    }
}
