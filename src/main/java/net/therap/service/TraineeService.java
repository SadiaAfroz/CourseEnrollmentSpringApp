package net.therap.service;

import net.therap.dao.TraineeDao;
import net.therap.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<Integer> getIds(Set<Trainee> traineeList) {
        List<Integer> allId = new ArrayList<>();
        for (Trainee t : traineeList) {
            allId.add(t.getId());
        }
        return allId;
    }

    public Set<Trainee> findNotEnrolledTrainees(int courseId) {
        Set<Trainee> enrolledTrainees = findAllByCourseId(courseId);
        List<Integer> enrolledTraineeIdList = getIds(enrolledTrainees);

        Set<Trainee> allTrainees = findAll();

        Set<Trainee> notEnrolledTraineeList = new HashSet<>();
        for (Trainee t : allTrainees) {
            if (!(enrolledTraineeIdList.contains(t.getId()))) {
                notEnrolledTraineeList.add(t);
            }
        }
        return notEnrolledTraineeList;
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
