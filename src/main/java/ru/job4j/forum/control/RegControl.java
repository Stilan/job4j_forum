package ru.job4j.forum.control;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {

    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String reg() {
        return "/reg";
    }

    @PostMapping("/reg")
    public String loginPage(@RequestParam String username,
                            @RequestParam String password, Model model) {
        User user = userService.getUserPasswordUsername(username, password);
        if (user != null) {
            model.addAttribute("errorMessage", "User exists");
            return "/reg";
        }
        user = new User(password, username);
        userService.save(user);
        return "/login";
    }

}
