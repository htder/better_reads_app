package io.javabrains.betterreads.userbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserBooksController {

    @Autowired
    UserBooksRepository userBooksRepository;

    @PostMapping(value = "/addUserBook")
    public ModelAndView addBookForUser(
            @RequestBody MultiValueMap<String, String> formData,
            @AuthenticationPrincipal OAuth2User principal
    ) {
        if (principal == null || principal.getAttribute("login") == null) {
            return null;
        }

        UserBooks userBooks = new UserBooks();
        UserBooksPrimaryKey key = new UserBooksPrimaryKey();

        key.setUserId(principal.getAttribute("login"));
        String bookId = formData.getFirst("bookId");
        key.setBookId(bookId);

        userBooks.setKey(key);
        userBooks.setStartedDate(formData.getFirst("startDate"));
        userBooks.setCompletedDate(formData.getFirst("completedDate"));
        userBooks.setRating(Integer.parseInt(formData.getFirst("rating")));
        userBooks.setReadingStatus(formData.getFirst("readingStatus"));

        userBooksRepository.save(userBooks);
        return new ModelAndView("redirect:/books/" + bookId);
    }

}
