package net.therap.validator;

import net.therap.dao.TraineeDao;
import net.therap.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author sadia.afroz
 * @since 4/18/21
 */
@Component
@Qualifier("traineeValidator")
public class TraineeValidator implements Validator {

    @Autowired
    private TraineeDao traineeDao;

//    public boolean isValidEmail(String traineeEmail) {
//        int count = traineeDao.isEmailExists(traineeEmail);
//        if (count == 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isValidId(int id) {
//        int count = traineeDao.isIdExists(id);
//        if (count == 0) {
//            return false;
//        }
//        return true;
//    }

//    public boolean isValidNameEmail(String name, String email) {
//        int count = traineeDao.isNameEmailExist(name, email);
//        if (count == 0) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Trainee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Trainee trainee = (Trainee) target;
        int isNameEmailExist = traineeDao.isNameEmailExist(trainee.getName(), trainee.getEmail());
        if (traineeDao.isEmailExists(trainee.getEmail()) != 0 || isNameEmailExist != 0) {
            errors.rejectValue("email", "email.exist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
    }
}
