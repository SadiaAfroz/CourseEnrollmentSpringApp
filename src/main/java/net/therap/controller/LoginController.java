package net.therap.controller;

import net.therap.dao.AdminDao;
import net.therap.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author sadia.afroz
 * @since 4/27/21
 */
@Controller
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loadWelcome(@Valid @ModelAttribute("admin") Admin admin,
                              BindingResult result, ModelMap model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", messageSource.getMessage("messages.loginwrongformat", null, " ", null));
            return "login";
        }
        AdminDao adminDao = new AdminDao();
        Admin adminFound = adminDao.check(admin.getUsername(), admin.getPassword());
        if (adminFound != null) {
            session.setAttribute("sesionid", adminFound.getId());
            return "redirect:/welcome";
        } else {
            model.addAttribute("errorMessage", messageSource.getMessage("messages.logininvalidcred", null, " ", null));
            return "login";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, editor);
    }

    @RequestMapping("/welcome")
    public String loadWelcome() {
        return "home";
    }
}