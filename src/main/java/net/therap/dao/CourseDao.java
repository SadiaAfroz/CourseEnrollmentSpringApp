package net.therap.dao;

import net.therap.model.Course;
import net.therap.model.Trainee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
@Repository
public class CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Course findById(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.id=:courseId", Course.class);
        return query.setParameter("courseId", courseId).getSingleResult();
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
        return count;
    }

    public int isIdExists(int id) {
        String sql = "SELECT COUNT(id) as count FROM Course WHERE id = :id";
        Query query = entityManager.createQuery(sql);

        int count = ((Long) query.setParameter("id", id).getSingleResult()).intValue();
        return count;
    }

    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Transactional
    public void saveOrUpdate(Course course) {
        entityManager.merge(course);
    }

    @Transactional
    public void remove(int courseId) {
        Course c = entityManager.find(Course.class, courseId);
        if (c != null) {
            c.removeCourseFromTrainees();
            entityManager.remove(c);
        }
    }
}
