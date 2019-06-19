package com.dmarchante.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CodeFellowshipController {
    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getHomePage(Principal p, Model m) {
        if (p != null) {
            AppUser user = appUserRepository.findByUsername(p.getName());
            m.addAttribute("user", user);
            return "userlogin";
        } else {
            m.addAttribute("principal", p);
            return "codefellowship";
        }
    }
}
