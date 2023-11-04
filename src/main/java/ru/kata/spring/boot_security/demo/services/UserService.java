package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

@Service
public interface UserService {
    void create(User user);

    void deleteById(long id);

    void update(User user);

    User findById(Long id);

    List<User> findAll();

    public User findUserByLogin(String login);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<String> parseFieldsErrors(List<FieldError> errors);
}
