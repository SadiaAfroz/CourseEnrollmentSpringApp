package net.therap.service;

import net.therap.dao.CourseDao;
import net.therap.dao.TraineeDao;
import net.therap.model.Course;
import net.therap.model.Trainee;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
@Service()
public class TraineeService {

    public Set<Trainee> findAllByCourseId(int courseId) {
        TraineeDao traineeDao = new TraineeDao();
        Set<Trainee> trainees = traineeDao.findAllByCourseId(courseId);
        return trainees;
    }

    public Set<Trainee> findAll() {
        TraineeDao traineeDao = new TraineeDao();
        Set<Trainee> trainees = new HashSet<>(traineeDao.findAll());
        return trainees;
    }

    public void save(Trainee trainee) {
        TraineeDao traineeDao = new TraineeDao();
        traineeDao.save(trainee);
    }

    public void saveOrUpdate(Trainee trainee) {
        TraineeDao traineeDao = new TraineeDao();
        traineeDao.saveOrUpdate(trainee);
    }

    public void remove(Trainee trainee) {
        TraineeDao traineeDao = new TraineeDao();
        traineeDao.remove(trainee);
    }


    public Trainee find(int traineeId) {
        TraineeDao traineeDao = new TraineeDao();
        return traineeDao.findById(traineeId);
    }

    public boolean isIdExist(int traineeId) {
        TraineeDao traineeDao = new TraineeDao();
        int count=traineeDao.isIdExists(traineeId);
        if(count==0) {
            return false;
        }
        return true;
    }
}
