package net.therap.controller;

import net.therap.model.Course;
import net.therap.model.Trainee;
import net.therap.service.CourseService;
import net.therap.service.TraineeService;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
public class TraineeController {

    @Autowired
    private TraineeService traineeService;


    @Autowired
    TraineeValidator traineeValidator;

    @GetMapping("/trainee")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model,
                       RedirectAttributes rttr) {
        if (id == 0 || traineeService.isIdExist(id)) {
            Trainee trainee = (id == 0) ? new Trainee() : traineeService.find(id);
            model.addAttribute("trainee", trainee);
            return "editTrainee";
        } else {
            rttr.addFlashAttribute("messageinvalidtraineeid", "Invalid Trainee id");
            return "redirect:/welcome";
        }
    }

    @PostMapping({"/trainee","/trainee/trainee"})
    public String process(@Valid @ModelAttribute("trainee") Trainee trainee,
                          BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("trainee", trainee);
            return "editTrainee";
        }
        boolean isValidNameEmail=traineeValidator.isValidNameEmail(trainee.getName(), trainee.getEmail());
        boolean isValidEmail=traineeValidator.isValidEmail(trainee.getEmail());
        if (isValidNameEmail && isValidEmail) {
            traineeService.saveOrUpdate(trainee);
            return "redirect:/traineelist";
        } else {
            model.addAttribute("trainee", trainee);
            model.addAttribute("messagesaveorupdate", "Duplicate Entry or Email");
            return "editTrainee";
        }
    }

    @RequestMapping(value = "/removetrainee")
    public String removeTrainee(@RequestParam("id") int traineeId) {
        traineeService.remove(traineeId);
        return "redirect:/traineelist";
    }

    @RequestMapping(value = "/traineelist", method = RequestMethod.GET)
    public String getAllTrainees(Model model) {
        Set<Trainee> trainees = traineeService.findAll();
        model.addAttribute("trainees", trainees);
        return "showTrainees";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
    }
}
