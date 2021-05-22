package net.therap.controller;

import net.therap.model.Trainee;
import net.therap.service.CourseEnrollmentService;
import net.therap.service.TraineeService;
import net.therap.validator.EnrollmentValidator;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class EnrollmentController {

    @Autowired
    private CourseEnrollmentService ces;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    EnrollmentValidator enrollmentValidator;

    @RequestMapping("/enrollment")
    public String showEnrollTrainee(@RequestParam("id") int courseId, ModelMap model) {
        Set<Trainee> trainees = traineeService.findNotEnrolledTrainees(courseId);
        model.addAttribute("trainees", trainees);
        model.addAttribute("courseid", courseId);
        return "enrollTrainee";
    }

    @RequestMapping(  "/enrolltrainee")
    public String enrollTrainee(@RequestParam("traineeid") int traineeId, @RequestParam("courseid") int courseId, RedirectAttributes rttr) {
        int numberOfTrainees = 1;
        if (enrollmentValidator.hasTraineeCapacity(courseId, numberOfTrainees)) {
            ces.enrollTrainees(courseId, traineeId);
            rttr.addFlashAttribute("messageenroll", "Successful Enrollment");
        } else {
            rttr.addFlashAttribute("messageenroll", "Capacity Not Supported");
        }
        return "redirect:/enrollment?id=" + courseId;
    }

    @RequestMapping("/removeenrollment")
    public String showRemoveTrainee(@RequestParam("id") int courseId, ModelMap model, RedirectAttributes rttr) {

        Set<Trainee> trainees = traineeService.findAllByCourseId(courseId);
        if (trainees.size() < 1) {
            rttr.addFlashAttribute("messageforaction", "No Trainees Assigned");
            return "redirect:/courselist";
        } else {
            model.addAttribute("trainees", trainees);
            model.addAttribute("courseid", courseId);
            return "removeTrainee";
        }
    }

    @RequestMapping( "/removetraineefromcourse")
    public String removeTrainee(@RequestParam("traineeid") int traineeId, @RequestParam("courseid") int courseId, RedirectAttributes rttr) {
        ces.removeTrainee(courseId, traineeId);
        rttr.addFlashAttribute("messageremove", "Successful Removal");
        return "redirect:/removeenrollment?id=" + courseId;
    }
}
