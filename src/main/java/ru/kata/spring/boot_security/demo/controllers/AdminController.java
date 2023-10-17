package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/admin/create")
    public String getTemplateForCreateUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("admin/create")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @RequestParam("listRoles") List<Long> roles) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        List<Role> rolesList = roleService.findByIdRoles(roles);
        user.setRoles(rolesList);
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String getTemplateForUpdatingUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        user.setPassword(null);
        return "edit";
    }

    @PutMapping("/admin/edit")
    public String updateUser(@Valid User user,
                             BindingResult bindingResult,
                             @RequestParam("listRoles") List<Long> roles) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        user.setRoles(roleService.findByIdRoles(roles));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

