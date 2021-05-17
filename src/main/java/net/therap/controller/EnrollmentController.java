package net.therap.controller;

import net.therap.service.CourseEnrollmentService;
import net.therap.validator.CourseValidator;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnrollmentController {

    @Autowired
    private CourseEnrollmentService ces;

    @RequestMapping(value = "/enrolltrainee", method = RequestMethod.POST)
    public String enrollTrainee(@RequestParam("traineeid") String traineeid, @RequestParam("courseid") String courseid, Model model) {
        int courseId = Integer.parseInt(courseid);
        int traineeId = Integer.parseInt(traineeid);
        String message = "";
        CourseValidator cv = new CourseValidator();
        if (cv.isValidId(courseId)) {
            int numberOfTrainees = 1;
            TraineeValidator traineeValidator = new TraineeValidator();
            if (traineeValidator.hasTraineeCapacity(courseId, numberOfTrainees)) {
                TraineeValidator tv = new TraineeValidator();
                if (tv.isValidId(traineeId)) {

                    ces.enrollTrainees(courseId, traineeId);
                    message = "Trainee : " + traineeId + " Enrolled to the Course :" + courseId + " Successfully";
                } else {
                    message = " Invalid Trinee Id";
                }
            } else {
                message = "Capacity Not Supported";
            }
        } else {
            message = "Invalid Course Id ";
        }
        model.addAttribute("message" + message);
        return "enrollment";
    }

    @RequestMapping(value = "/removetraineefromcourse", method = RequestMethod.POST)
    public String removeTrainee(@RequestParam("traineeid") String traineeid, @RequestParam("courseid") String courseid, Model model) {
        int courseId = Integer.parseInt(courseid);
        int traineeId = Integer.parseInt(traineeid);
        String message = "";
        CourseValidator cv = new CourseValidator();
        if (cv.isValidId(courseId)) {
            TraineeValidator tv = new TraineeValidator();
            if (tv.isValidId(traineeId)) {
                ces.removeTrainee(courseId, traineeId);
                message = "Trainee : " + traineeId + " Removed from Course :" + courseId + " Successfully";
            } else {
                message = "Invalid Trainee Id";
            }
        } else {
            message = "Invalid Course Id";
        }
        model.addAttribute("message" + message);
        return "enrollment";
    }
}
