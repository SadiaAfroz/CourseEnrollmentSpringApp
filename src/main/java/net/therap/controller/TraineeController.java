package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.TraineeService;
import net.therap.validator.CourseValidator;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

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
            return "login";
        }
        int traineeId = trainee.getId();
        System.out.println("This is mee " + traineeId);
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

//    @RequestMapping(value = "/addnewtrainee")
//    public String addTrainee(@ModelAttribute("traineedetails") Trainee trainee,
//                             BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "login";
//        }
//        String name = trainee.getName();
//        String email = trainee.getEmail();
//        String message = "";
//        TraineeValidator traineeValidator = new TraineeValidator();
//        if (traineeValidator.isValidName(name)) {
//            traineeService.save(trainee);
//            message = "Trainee Added Successfully";
//        } else {
//            message = "Trainee Name already exits";
//        }
//        model.addAttribute("messageaddnewtrainee", message);
//        return "redirect:/trainees";
//    }

    @RequestMapping(value = "/updatetraineeemail")
    public String updateTraineeEmail(@ModelAttribute("traineedetails") Trainee trainee,
                                     BindingResult result, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "login";
        }
        String message = "";
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidId(trainee.getId())) {
            traineeService.saveOrUpdate(trainee);
            message = "Trainee Email updated Successfully";
        } else {
            message = "Invalid Trainee Id";
        }
        rattrs.addFlashAttribute("messageupdatetraineeemail", message);
        return "redirect:/trainees";
    }

    @RequestMapping(value = "/updatetraineename")
    public String updateTraineeName(@ModelAttribute("traineedetails") Trainee trainee,
                                    BindingResult result, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "login";
        }
        String message = "";
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidId(trainee.getId())) {
            traineeService.saveOrUpdate(trainee);
            message = "Trainee Name updated Successfully";
        } else {
            message = "Invalid Trainee Id";
        }
        rattrs.addFlashAttribute("messageupdatetraineename", message);
        return "redirect:/trainees";
    }

    @RequestMapping(value = "/removetrainee")
    public String removeTrainee(@ModelAttribute("traineedetails") Trainee trainee,
                                BindingResult result, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "login";
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
        return "redirect:/trainees";
    }

    @RequestMapping(value = "/gettrainees", method = RequestMethod.GET)
    public String getAllTrainees(Model model) {
        Set<Trainee> trainees = traineeService.findAll();
        model.addAttribute("trainees", trainees);
        return "showTrainees";
    }
}
