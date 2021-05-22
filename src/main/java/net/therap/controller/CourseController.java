package net.therap.controller;

import net.therap.model.Course;
import net.therap.service.CourseService;
import net.therap.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    @Qualifier("courseValidator")
    private CourseValidator courseValidator;

    @Autowired
    private Environment env;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
        binder.setValidator(courseValidator);
    }

    @RequestMapping(value = "/showenrolledcourses")
    public String getCourseDetails(@RequestParam("id") int traineeId, ModelMap model, RedirectAttributes rttr) {

        Set<Course> courseList = courseService.findAllByTraineeId(traineeId);
        if (courseList.size() < 1) {
            rttr.addFlashAttribute("messagefortraineeaction", env.getProperty("messages.messagefortraineeaction"));
            return "redirect:/traineelist";
        } else {
            model.addAttribute("courseList", courseList);
            model.addAttribute("fullFlag", 0);
            return "courseList";
        }
    }

    @GetMapping("/course")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
        Course course = (id == 0) ? new Course() : courseService.find(id);
        model.addAttribute("course", course);
        return "course";
    }

    @PostMapping("/course")
    public String process(@Valid @ModelAttribute("course") Course course,
                          BindingResult result, RedirectAttributes rattrs, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            return "course";
        }
        courseService.saveOrUpdate(course);
        return "redirect:/courselist";
    }

    @RequestMapping(value = "/courseremove")
    public String remove(@RequestParam("id") int courseId) {
        courseService.remove(courseId);
        return "redirect:/courselist";
    }

    @RequestMapping(value = "/courselist", method = RequestMethod.GET)
    public String showAll(ModelMap model) {
        Set<Course> courseList = courseService.findAll();
        model.addAttribute("fullFlag", 1);
        model.addAttribute("courseList", courseList);
        return "courseList";
    }
}

