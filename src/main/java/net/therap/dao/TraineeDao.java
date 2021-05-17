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
public class TraineeDao {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public TraineeDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("courseEnrollment");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Set<Trainee> findAllByCourseId(int courseId) {
        Course course = (Course) entityManager.find(Course.class, courseId);
        return course.getTrainees();
    }

    public Trainee findByName(String name) {
        TypedQuery<Trainee> query = entityManager.createQuery("SELECT t FROM Trainee t WHERE t.name=:name", Trainee.class);
        Trainee trainee = query.setParameter("name", name).getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return trainee;
    }

    public List<Trainee> findAll() {
        TypedQuery<Trainee> query = entityManager.createQuery("SELECT t FROM Trainee t ORDER BY t.name ASC", Trainee.class);
        return query.getResultList();
    }

    public int isNameExists(String name) {
        String sql = "SELECT COUNT(id) as count FROM Trainee WHERE name=:name";
        int count = 0;
        Query query = entityManager.createQuery(sql);

        count = ((Long) query.setParameter("name", name).getSingleResult()).intValue();

        entityManager.close();
        entityManagerFactory.close();
        return count;
    }

    public int isIdExists(int id) {
        String sql = "SELECT COUNT(id) as count FROM Trainee WHERE id=:id";
        int count = 0;
        Query query = entityManager.createQuery(sql);

        count = ((Long) query.setParameter("id", id).getSingleResult()).intValue();

        entityManager.close();
        entityManagerFactory.close();
        return count;
    }

    public void save(Trainee trainee) {
        entityManager.getTransaction().begin();
        entityManager.persist(trainee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        System.out.println("Trainee Added");
    }

    public void saveOrUpdate(Trainee trainee) {
        Trainee t = entityManager.find(Trainee.class, trainee.getId());
        if (trainee.getName() != null) {
            t.setName(trainee.getName());
        }
        if (trainee.getEmail() != null) {
            t.setEmail(trainee.getEmail());
        }

        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();

        System.out.println("Trainee Name/Email Updated");
        entityManager.close();
        entityManagerFactory.close();
    }

    public void remove(Trainee trainee) {
        Trainee t = entityManager.find(Trainee.class, trainee.getId());
        if (t != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            System.out.println("Trainee Deleted");
        } else {
            System.out.println("Invalid Trainee Id");
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
