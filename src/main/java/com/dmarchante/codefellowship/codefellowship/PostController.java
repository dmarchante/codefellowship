package com.dmarchante.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/posts")
    public String getPosts(Principal p, Model m) {
        AppUser currentUser = appUserRepository.findByUsername(p.getName());

        m.addAttribute("currentUser", currentUser);
        return "posts";
    }

    @GetMapping("/posts/add")
    public String getPostCreate() {
        return "createpost";
    }

    @PostMapping("/posts/add")
    public RedirectView addPost(String body, Date timeStamp, Principal p) {
        Post post = new Post();
        post.body = body;
        post.timeStamp = timeStamp;
        post.user = appUserRepository.findByUsername(p.getName());
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model m, Principal p) {
        Post post = postRepository.findById(id).get();
        if (post.getUser().getUsername().equals(p.getName())) {
            m.addAttribute("post", post);
            return "post";
        } else {
            throw new PostForbiddenException("That dinosaur does not belong to you.");
        }
    }
}

@ResponseStatus(value = HttpStatus.FORBIDDEN)
class PostForbiddenException extends RuntimeException {
    public PostForbiddenException(String s) {
        super(s);
    }
}
