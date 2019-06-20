package com.dmarchante.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CodeFellowshipController {
    @Autowired
    AppUserRepository appUserRepository;

//    @GetMapping("/")
//    public String getRootPage(Principal p, Model m) {
//        if (p != null) {
//            AppUser user = appUserRepository.findByUsername(p.getName());
//            m.addAttribute("user", user);
//            return "myprofile";
//        } else {
//            m.addAttribute("principal", p);
//            return "codefellowship";
//        }
//    }

    @GetMapping("/")
    public RedirectView getRootPage(Principal p, Model m) {
        if (p != null) {
            return new RedirectView("/myprofile");
        } else {
            return new RedirectView("/home");
        }
    }

    @GetMapping ("/home")
    public String getHomePage(Principal p, Model m) {
        m.addAttribute("principal", p);
        return "home";
    }

    @GetMapping ("/myprofile")
    public String getProfilePage(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("principal", p);
        m.addAttribute("user", user);
        return "myprofile";
    }
}
