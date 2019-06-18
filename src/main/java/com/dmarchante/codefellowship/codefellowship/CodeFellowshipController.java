package com.dmarchante.codefellowship.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CodeFellowshipController {

    @GetMapping("/")
    public String getCodeFellowship(Principal p, Model m) {
        m.addAttribute("principal", p);
        return "codefellowship";
    }
}
