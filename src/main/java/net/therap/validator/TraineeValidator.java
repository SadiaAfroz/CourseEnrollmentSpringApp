package net.therap.validator;

import net.therap.dao.TraineeDao;

import static net.therap.util.Capacity.MAX_TRAINEE_TO_COURSE_ENROLL;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
public class TraineeValidator {
    private TraineeDao traineeDao;

    public TraineeValidator() {
        traineeDao = new TraineeDao();
    }

    public boolean isValidName(String traineeName) {
        int count = traineeDao.isNameExists(traineeName);
        if (count == 0) {
            return true;
        }
        return false;
    }

    public boolean isValidId(int id) {
        int count = traineeDao.isIdExists(id);
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean hasTraineeCapacity(int courseId, int numberOfTrainees) {
        int countTrainees = traineeDao.findAllByCourseId(courseId).size();
        if ((countTrainees + numberOfTrainees) <= MAX_TRAINEE_TO_COURSE_ENROLL) {
            return true;
        }
        return false;
    }
}
