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
public class TraineeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Trainee findById(int traineeId) {

        Trainee trainee = entityManager.find(Trainee.class, traineeId);
        return trainee;
    }

    public Set<Trainee> findAllByCourseId(int courseId) {
        Course course = (Course) entityManager.find(Course.class, courseId);
        return course.getTrainees();
    }

    public Trainee findByName(String name) {
        TypedQuery<Trainee> query = entityManager.createQuery("SELECT t FROM Trainee t WHERE t.name=:name", Trainee.class);
        Trainee trainee = query.setParameter("name", name).getSingleResult();
        return trainee;
    }

    public List<Trainee> findAll() {
        TypedQuery<Trainee> query = entityManager.createQuery("SELECT t FROM Trainee t ORDER BY t.name ASC", Trainee.class);
        return query.getResultList();
    }

    public int isEmailExists(String email) {
        String sql = "SELECT COUNT(id) as count FROM Trainee WHERE email=:email";
        int count = 0;
        Query query = entityManager.createQuery(sql);

        count = ((Long) query.setParameter("email", email).getSingleResult()).intValue();
        return count;
    }

    public int isIdExists(int id) {
        String sql = "SELECT COUNT(id) as count FROM Trainee WHERE id=:id";
        int count = 0;
        Query query = entityManager.createQuery(sql);

        count = ((Long) query.setParameter("id", id).getSingleResult()).intValue();
        return count;
    }

    public int isNameEmailExist(String name, String email) {
        String sql = "SELECT COUNT(id) as count FROM Trainee WHERE name=:name AND email=:email";
        int count = 0;
        Query query = entityManager.createQuery(sql);

        count = ((Long) query.setParameter("name", name)
                .setParameter("email", email).getSingleResult()).intValue();
        return count;
    }

    @Transactional
    public void save(Trainee trainee) {
        entityManager.persist(trainee);
        System.out.println("Trainee Added");
    }

    @Transactional
    public void saveOrUpdate(Trainee trainee) {
        //Trainee t = entityManager.find(Trainee.class, trainee.getId());
//        if (trainee.getName() != null) {
//            t.setName(trainee.getName());
//        }
//        if (trainee.getEmail() != null) {
//            t.setEmail(trainee.getEmail());
//        }

        entityManager.merge(trainee);
        System.out.println("Trainee Name/Email Updated");
    }

    @Transactional
    public void remove(int traineeId) {
        Trainee t = entityManager.find(Trainee.class, traineeId);
        entityManager.remove(t);
    }
}
