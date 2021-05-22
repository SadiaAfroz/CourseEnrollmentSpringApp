package net.therap.controller;

import net.therap.model.Trainee;
import net.therap.service.TraineeService;
import net.therap.validator.TraineeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("traineeValidator")
    TraineeValidator traineeValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
        binder.setValidator(traineeValidator);
    }

    @GetMapping("/trainee")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int traineeId, Model model,
                       RedirectAttributes rttr) {
        Trainee trainee = (traineeId == 0) ? new Trainee() : traineeService.find(traineeId);
        model.addAttribute("trainee", trainee);
        return "trainee";
    }

    @PostMapping("/trainee")
    public String process(@Valid @ModelAttribute("trainee") Trainee trainee,
                          BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("trainee", trainee);
            return "trainee";
        }
        traineeService.saveOrUpdate(trainee);
        return "redirect:/traineelist";
    }

    @RequestMapping(value = "/traineeremove")
    public String remove(@RequestParam("id") int traineeId) {
        traineeService.remove(traineeId);
        return "redirect:/traineelist";
    }

    @RequestMapping(value = "/traineelist", method = RequestMethod.GET)
    public String showAll(ModelMap model) {
        Set<Trainee> traineeList = traineeService.findAll();
        model.addAttribute("traineeList", traineeList);
        return "traineeList";
    }
}
