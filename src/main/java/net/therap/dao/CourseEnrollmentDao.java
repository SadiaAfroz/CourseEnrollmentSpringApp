package net.therap.dao;

import net.therap.model.Course;
import net.therap.model.Trainee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Repository
public class CourseEnrollmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addTraineeList(int courseId, int traineeId) {
        Course c = (Course) entityManager.find(Course.class, courseId);
        Trainee t = (Trainee) entityManager.find(Trainee.class, traineeId);
        c.addTrainee(t);
        entityManager.merge(c);
    }

    @Transactional
    public void removeTrainee(int courseId, int traineeId) {
        Course c = (Course) entityManager.find(Course.class, courseId);
        Trainee t = (Trainee) entityManager.find(Trainee.class, traineeId);
        c.removeTrainee(t);
    }
}
