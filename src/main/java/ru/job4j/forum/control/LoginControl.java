package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;


@Controller
public class LoginControl {

    private final UserService userService;

    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginPage(@RequestParam String username,
                            @RequestParam String password, Model model) {
        User user = userService.getUserPasswordUsername(username, password);
        if (user != null) {
            return "redirect:/";
        }
        model.addAttribute("errorMessage", "Incorrect login or password");
        return "/login";
    }
}
