package net.therap.dao;

import net.therap.model.Course;
import net.therap.model.Trainee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
public class CourseEnrollmentDao {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CourseEnrollmentDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("courseEnrollment");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void addTraineeList(int courseId, int traineeId) {

        Course c = (Course) entityManager.find(Course.class, courseId);
        Trainee t = (Trainee) entityManager.find(Trainee.class, traineeId);
        c.addTrainee(t);

        entityManager.getTransaction().begin();
        entityManager.merge(c);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void removeTrainee(int courseId, int traineeId) {
        Course c = (Course) entityManager.find(Course.class, courseId);
        Trainee t = (Trainee) entityManager.find(Trainee.class, traineeId);

        entityManager.getTransaction().begin();
        c.removeTrainee(t);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
