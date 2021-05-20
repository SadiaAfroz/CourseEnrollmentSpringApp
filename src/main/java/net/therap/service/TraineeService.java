package net.therap.service;

import net.therap.dao.TraineeDao;
import net.therap.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
@Service()
public class TraineeService {

    @Autowired
    private TraineeDao traineeDao;

    public Set<Trainee> findAllByCourseId(int courseId) {
        Set<Trainee> trainees = traineeDao.findAllByCourseId(courseId);
        return trainees;
    }

    public Set<Trainee> findAll() {
        Set<Trainee> trainees = new HashSet<>(traineeDao.findAll());
        return trainees;
    }

    public void save(Trainee trainee) {
        traineeDao.save(trainee);
    }

    public void saveOrUpdate(Trainee trainee) {
        traineeDao.saveOrUpdate(trainee);
    }

    public void remove(int traineeId) {
        traineeDao.remove(traineeId);
    }

    public Trainee find(int traineeId) {
        return traineeDao.findById(traineeId);
    }

    public boolean isIdExist(int traineeId) {
        int count = traineeDao.isIdExists(traineeId);
        if (count == 0) {
            return false;
        }
        return true;
    }
}
