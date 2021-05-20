package net.therap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author sadia.afroz
 * @since 5/17/21
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sesionid");
        session.invalidate();
        return "redirect: login";
    }
}
