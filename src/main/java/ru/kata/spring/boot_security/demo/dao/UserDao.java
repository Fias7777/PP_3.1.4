package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    User findById(int id);
    List<User> findAll();
    void update(User user);
    void delete(int id);
    User findUserByUsername(String username);
}
