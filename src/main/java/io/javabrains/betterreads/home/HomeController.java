package io.javabrains.betterreads.home;

import io.javabrains.betterreads.user.BooksByUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @Autowired
    BooksByUserRepository booksByUserRepository;

    public String home(@AuthenticationPrincipal OAuth2User principal) {
        String userId = principal.getAttribute("login");
        if (principal == null || userId == null) {
            return "index";
        }
        return "home";
    }
}
