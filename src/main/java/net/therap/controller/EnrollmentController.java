package net.therap.controller;

import net.therap.service.CourseEnrollmentService;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class EnrollmentController {

    @Autowired
    private CourseEnrollmentService ces;

    @PostMapping(value = "/enrollment/enrolltrainee")
    public String enrollTrainee(@RequestParam("traineeid") Integer traineeId, @RequestParam("courseid") Integer courseId, RedirectAttributes rttr) {
        int numberOfTrainees = 1;
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.hasTraineeCapacity(courseId, numberOfTrainees)) {
            ces.enrollTrainees(courseId, traineeId);
            rttr.addFlashAttribute("messageenroll", "Successful Enrollment");
        } else {
            rttr.addFlashAttribute("messageenroll", "Capacity Not Supported");
        }
        return "redirect:/enrollment?id=" + courseId;
    }

    @PostMapping(value = "/removeenrollment/removetraineefromcourse")
    public String removeTrainee(@RequestParam("traineeid") Integer traineeId, @RequestParam("courseid") Integer courseId, RedirectAttributes rttr) {
        ces.removeTrainee(courseId, traineeId);
        rttr.addFlashAttribute("messageremove", "Successful Removal");
        return "redirect:/removeenrollment?id=" + courseId;
    }
}
