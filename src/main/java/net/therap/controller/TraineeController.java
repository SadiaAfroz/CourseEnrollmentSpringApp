package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.TraineeService;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class TraineeController {

    @Autowired
    private TraineeService traineeService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/coursedetailsbytraineeid")
    public String getCourseDetails(@ModelAttribute("traineedetails") Trainee trainee,
                                   BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "redirect:/welcome";
        }
        int traineeId = trainee.getId();
        TraineeValidator tv = new TraineeValidator();
        if (tv.isValidId(traineeId)) {
            Set<Course> courses = courseService.findAllByTraineeId(traineeId);
            if (courses.size() < 1) {
                model.addAttribute("messagecourse", "No Course Assigned to the trainee");
            } else {
                model.addAttribute("courses", courses);
                return "showCourses";
            }
        } else {
            model.addAttribute("messagecourse", "Invalid trainee id");
        }
        return "trainee";
    }

    @GetMapping("/trainees")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, Model model,
                       RedirectAttributes rttr) {
        if (id == 0 || traineeService.isIdExist(id)) {
            Trainee trainee = (id == 0) ? new Trainee() : traineeService.find(id);
            model.addAttribute("traineedetails", trainee);
            return "trainee";
        } else {
            rttr.addFlashAttribute("messageinvalidtraineeid", "Invalid Trainee id");
            return "redirect:/welcome";
        }
    }

    @PostMapping("/trainees")
    public String process(@ModelAttribute("traineedetails") Trainee trainee,
                          BindingResult result, ModelMap model, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "redirect:/welcome";
        }
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidNameEmail(trainee.getName(), trainee.getEmail()) && traineeValidator.isValidEmail(trainee.getEmail())) {
            traineeService.saveOrUpdate(trainee);
            return "redirect:/gettrainees";
        } else {
            rattrs.addFlashAttribute("messagesaveorupdate", "Duplicate Entry or Email");
            return "redirect:/trainees?id=" + trainee.getId();
        }
    }

    @RequestMapping(value = "/removetrainee")
    public String removeTrainee(@ModelAttribute("traineedetails") Trainee trainee,
                                BindingResult result, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "redirect:/welcome";
        }
        String message = "";
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidId(trainee.getId())) {
            traineeService.remove(trainee);
            message = "Trainee Removed Successfully";
        } else {
            message = "Invalid Id";
        }
        rattrs.addFlashAttribute("messageremovetrainee", message);
        return "redirect:/trainees?id=" + trainee.getId();
    }

    @RequestMapping(value = "/gettrainees", method = RequestMethod.GET)
    public String getAllTrainees(Model model) {
        Set<Trainee> trainees = traineeService.findAll();
        model.addAttribute("trainees", trainees);
        return "showTrainees";
    }
}
