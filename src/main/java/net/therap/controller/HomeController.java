package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String loadCourseModule(Model model) {
        model.addAttribute("coursedetails", new Course());
        return "course";
    }

    @RequestMapping(value = "/trainees", method = RequestMethod.GET)
    public String loadTraineeModule(Model model) {
        model.addAttribute("traineedetails", new Trainee());
        return "trainee";
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
