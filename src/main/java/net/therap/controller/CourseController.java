package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.TraineeService;
import net.therap.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseValidator courseValidator;

    @RequestMapping(value = "/showenrolledcourses")
    public String getCourseDetails(@RequestParam("id") int traineeId, ModelMap model, RedirectAttributes rttr) {

        Set<Course> courses = courseService.findAllByTraineeId(traineeId);
        if (courses == null) {
            rttr.addFlashAttribute("messagefortraineeaction", "No Course Assigned to the trainee");
            return "redirect:/traineelist";
        } else {
            model.addAttribute("courses", courses);
            return "showTraineeCourses";
        }
    }

    @GetMapping("/course")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model,
                       RedirectAttributes rttr) {

        if (id == 0 || courseService.isIdExist(id)) {
            Course course = (id == 0) ? new Course() : courseService.find(id);
            model.addAttribute("course", course);
            return "courseEdit";
        } else {
            rttr.addFlashAttribute("messageinvalidcourseid", "Invalid Course id");
            return "redirect:/welcome";
        }
    }

    @PostMapping({"/course", "/course/course"})
    public String process(@Valid @ModelAttribute("course") Course course,
                          BindingResult result, RedirectAttributes rattrs, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            return "courseEdit";
        }
        if (courseValidator.isValidTitle(course.getTitle())) {
            System.out.println("This is course post , id :" + course.getId() + " title: " + course.getTitle());
            courseService.saveOrUpdate(course);
            return "redirect:/courselist";
        } else {
            model.addAttribute("course", course);
            model.addAttribute("messagesaveorupdate", "Title Already exist");
            return "courseEdit";
        }
    }

    @RequestMapping(value = "/removecourse")
    public String removeCourse(@RequestParam("id") int courseId) {
        courseService.remove(courseId);
        return "redirect:/courselist";
    }

    @RequestMapping(value = "/courselist", method = RequestMethod.GET)
    public String getAllCourses(Model model) {
        Set<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "showCourses";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
    }
}

