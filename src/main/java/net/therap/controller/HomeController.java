package net.therap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/getallcourses", method = RequestMethod.GET)
    public String getAllCourses(Model model) {
        return "redirect:/getcourses";
    }

    @RequestMapping(value = "/getalltrainees")
    public String getAllTrainees(Model model) {
        return "redirect:/gettrainees";
    }
}
