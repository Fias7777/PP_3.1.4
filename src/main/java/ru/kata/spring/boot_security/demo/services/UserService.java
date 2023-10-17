package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

@Service
public interface UserService {
    void create(User user);

    void deleteById(Long id);

    void update(User user);

    User findById(Long id);

    List<User> findAll();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
