package com.dmarchante.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.sql.Date;

@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public RedirectView addNewUser(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam Date birthDate,
                                   @RequestParam String bio) {
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, birthDate, bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @GetMapping("/users")
    public String getAllUsers(Model m) {
        Iterable<AppUser> users = appUserRepository.findAll();
        m.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Model m) {
        AppUser user = appUserRepository.findById(id).get();
        m.addAttribute("user", user);
        return "userprofile";
    }
}
