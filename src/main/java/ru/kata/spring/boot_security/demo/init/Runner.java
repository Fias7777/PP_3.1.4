//package ru.kata.spring.boot_security.demo.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.entities.Role;
//import ru.kata.spring.boot_security.demo.entities.User;
//import ru.kata.spring.boot_security.demo.services.RoleService;
//import ru.kata.spring.boot_security.demo.services.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class Runner implements CommandLineRunner {
//    private final UserService userService;
//
//    private final RoleService roleService;
//
//    @Autowired
//    public Runner(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        roleService.addRole(new Role("ADMIN"));
//        roleService.addRole(new Role("USER"));
//
//        List<Role> adminRole = new ArrayList<>();
//        List<Role> userRole = new ArrayList<>();
//        adminRole.add(roleService.findById(1L));
//        adminRole.add(roleService.findById(2L));
//        userService.create(new User("Ivan", "Ivanov", 45, "ADMIN", "ADMIN", adminRole));
//
//    }
//}
//
