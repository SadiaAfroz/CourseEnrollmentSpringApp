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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private TraineeService traineeService;

    @GetMapping("/trainees")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, Model model,
                       RedirectAttributes rttr) {
        if(id==0 || traineeService.isIdExist(id)){
            Trainee trainee = (id == 0) ? new Trainee() : traineeService.find(id);
            model.addAttribute("traineedetails", trainee);
            System.out.println("Hello me here in Homecontroller" + trainee.getId());
            return "trainee";
        }
        else{
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
        System.out.println("Before Validation:  "+ trainee.getId()+" "+ trainee.getName()+" "+ trainee.getEmail());
        TraineeValidator traineeValidator = new TraineeValidator();
        if (traineeValidator.isValidNameEmail(trainee.getName(), trainee.getEmail()) && traineeValidator.isValidEmail(trainee.getEmail())) {
            traineeService.saveOrUpdate(trainee);
            return "redirect:/gettrainees";
        } else {
            rattrs.addFlashAttribute("messagesaveorupdate", "Duplicate Entry or Email");
            System.out.println("This is trainee id "+ trainee.getId());
            //rattrs.addFlashAttribute("id", trainee.getId());
            return "redirect:/trainees";
        }


//        CourseValidator courseValidator = new CourseValidator();
//        if (courseValidator.isValidTitle(course.getTitle())) {
//            traineeService.saveOrUpdate(course);
//            return "redirect:/getcourses";
//        } else {
//            rattrs.addFlashAttribute("messagesaveorupdate", "Title already exits");
//            return "redirect:/courses?id=" + course.getId();
//        }
    }

    @RequestMapping(value = "/enrollment")
    public String loadEnrollmentModule(Model model) {
        return "enrollment";
    }

    @RequestMapping(value = "/getallcourses", method = RequestMethod.GET)
    public String getAllCourses(Model model) {
        return "redirect:/getcourses";
    }

    @RequestMapping(value = "/getalltrainees")
    public String getAllTrainees(Model model) {
        return "redirect:/gettrainees";
    }
}
