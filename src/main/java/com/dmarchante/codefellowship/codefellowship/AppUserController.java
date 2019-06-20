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

import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
    public String getAllUsers(Model m, Principal p) {
        ArrayList<AppUser> users = (ArrayList<AppUser>) appUserRepository.findAll();
        AppUser currentUser = appUserRepository.findByUsername(p.getName());

        m.addAttribute("principal", p);
        m.addAttribute("users", users);

        users.remove(currentUser);

        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Model m, Principal p) {
        AppUser user = appUserRepository.findById(id).get();

        m.addAttribute("principal", p);
        m.addAttribute("user", user);

        return "userprofile";
    }

    @PostMapping("/users/{id}/followee")
    public RedirectView addFollowee(@PathVariable Long id, Principal p) {
        AppUser currentUser = appUserRepository.findByUsername(p.getName());
        AppUser newFollowee = appUserRepository.findById(id).get();

        currentUser.getFollowee().add(newFollowee);
        appUserRepository.save(currentUser);

        return new RedirectView("/users/" + id);
    }
}
