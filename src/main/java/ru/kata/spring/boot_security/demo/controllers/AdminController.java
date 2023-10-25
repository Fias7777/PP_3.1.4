package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/create")
    public String templateForCreateUser(ModelMap model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
                             @RequestParam("listRoles") List<Long> roles) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        User user =new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        List<Role> rolesList = roleService.findByIdRoles(roles);
        user.setRoles(rolesList);
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String templateForUpdatingUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        user.setPassword(null);
        return "edit";
    }

    @PutMapping("/edit")
    public String updateUser(@Valid UserDto userDto,
                             BindingResult bindingResult,
                             @RequestParam("listRoles") List<Long> roles) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        User user = userService.findById(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setLogin(userDto.getLogin());

        user.setRoles(roleService.findByIdRoles(roles));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

