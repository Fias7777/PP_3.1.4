package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String pageForUser(Model model, Principal principal) {
        List<User> userList = userService.findAll();
        User user = userList
                .stream()
                .filter(user1 -> user1.getLogin().equals(principal.getName()))
                .findFirst().orElseThrow(RuntimeException::new);


        model.addAttribute("user", user);
        model.addAttribute("roles", user.getStringRoles());
        model.addAttribute("users", userList);

        return "user";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
