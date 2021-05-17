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

import java.util.Set;

@Controller
public class CourseController {


    @Autowired
    private CourseService courseService;
    @Autowired
    private TraineeService traineeService;

    @RequestMapping("/traineedetailsbycourseid")
    public String getTraineeDetails(@ModelAttribute("coursedetails") Course course,
                                    BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "login";
        }
        CourseValidator cv = new CourseValidator();
        int courseId = course.getId();
        if (cv.isValidId(courseId)) {
            Set<Trainee> trainees = traineeService.findAllByCourseId(courseId);
            if (trainees.size() < 1) {
                model.addAttribute("messagetrainee", "No Trainee Assigned to the Course");
            } else {
                model.addAttribute("trainees", trainees);
                return "showTrainees";
            }
        } else {
            model.addAttribute("messagetrainee", "Invalid Course id");
        }
        return "course";
    }

    @GetMapping("/courses")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, Model model,
                       RedirectAttributes rttr) {

        if (id == 0 || courseService.isIdExist(id)) {
            Course course = (id == 0) ? new Course() : courseService.find(id);
            model.addAttribute("coursedetails", course);
            System.out.println("Hello me here in Homecontroller" + course.getId());
            return "course";
        } else {
            rttr.addFlashAttribute("messageinvalidcourseid", "Invalid Course id");
            return "redirect:/welcome";
        }
    }

    @PostMapping("/courses")
    public String process(@ModelAttribute("coursedetails") Course course, BindingResult result, RedirectAttributes rattrs) {

        System.out.println("This is course id " + course.getId());
        if (result.hasErrors()) {
            return "redirect:/welcome";
        }
        CourseValidator courseValidator = new CourseValidator();
        if (courseValidator.isValidTitle(course.getTitle())) {
            courseService.saveOrUpdate(course);
            return "redirect:/getcourses";
        } else {
            rattrs.addFlashAttribute("messagesaveorupdate", "Title already exits");
//            rattrs.addFlashAttribute("id", course.getId());
            return "redirect:/courses?id="+course.getId();
        }
    }
    @RequestMapping(value = "/removecourse")
    public String removeCourse(@ModelAttribute("coursedetails") Course course,
                               BindingResult result, RedirectAttributes rattrs) {
        if (result.hasErrors()) {
            return "login";
        }
        String message = "";
        CourseValidator courseValidator = new CourseValidator();
        if (courseValidator.isValidId(course.getId())) {
            courseService.remove(course);
            message = "Course Removed Successfully";
        } else {
            message = "Invalid Id";
        }
        rattrs.addFlashAttribute("messageremovecourse", message);

        return "redirect:/courses";
    }

    @RequestMapping(value = "/getcourses", method = RequestMethod.GET)
    public String getAllCourses(Model model) {
        Set<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "showCourses";
    }
}

