package net.therap.dao;

import net.therap.model.Course;
import net.therap.model.Trainee;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public class CourseDao {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CourseDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("courseEnrollment");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Course findById(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.id=:courseId", Course.class);
        Course course = query.setParameter("courseId", courseId).getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return course;
    }

    public Set<Course> findAllByTraineeId(int traineeId) {
        Trainee trainee = (Trainee) entityManager.find(Trainee.class, traineeId);
        return trainee.getCourses();
    }

    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c ORDER BY c.title ASC", Course.class);
        return query.getResultList();
    }

    public int isTitleExists(String courseTitle) {
        String sql = "SELECT COUNT(id) as count FROM Course WHERE title = :title";
        Query query = entityManager.createQuery(sql);

        int count = ((Long) query.setParameter("title", courseTitle).getSingleResult()).intValue();

        entityManager.close();
        entityManagerFactory.close();

        return count;
    }

    public int isIdExists(int id) {
        String sql = "SELECT COUNT(id) as count FROM Course WHERE id = :id";
        Query query = entityManager.createQuery(sql);

        int count = ((Long) query.setParameter("id", id).getSingleResult()).intValue();

        entityManager.close();
        entityManagerFactory.close();

        return count;
    }

    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("Course Added");
    }

    public void saveOrUpdate(Course course) {
        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
        System.out.println("Course Updated");

        entityManager.close();
        entityManagerFactory.close();
    }

    public void remove(Course course) {
        Course c = entityManager.find(Course.class, course.getId());
        if (c != null) {
            entityManager.getTransaction().begin();
            c.removeCourseFromTrainees();
            entityManager.remove(c);
            entityManager.getTransaction().commit();
            System.out.println("Course Deleted");
        } else {
            System.out.println("Invalid Course Id");
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
