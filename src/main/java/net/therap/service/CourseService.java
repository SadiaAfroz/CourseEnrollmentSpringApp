package net.therap.service;

import net.therap.dao.CourseDao;
import net.therap.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public Set<Course> findAll() {
        Set<Course> courses = new HashSet<>(courseDao.findAll());
        return courses;
    }

    public Set<Course> findAllByTraineeId(int traineeId) {
        Set<Course> courses = courseDao.findAllByTraineeId(traineeId);

        return courses;
    }

    public void save(Course course) {
        courseDao.save(course);
    }

    public void saveOrUpdate(Course course) {
        courseDao.saveOrUpdate(course);
    }

    public void remove(int courseId) {
        courseDao.remove(courseId);
    }

    public Course find(int courseId) {
        return courseDao.findById(courseId);
    }

    public boolean isIdExist(int courseId) {
        int count = courseDao.isIdExists(courseId);
        if (count == 0) {
            return false;
        }
        return true;
    }
}
