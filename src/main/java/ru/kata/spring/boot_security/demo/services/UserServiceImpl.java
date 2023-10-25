package ru.kata.spring.boot_security.demo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        roleService.saveRoles(user.getRoles());
        userDao.save(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.update(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User findById(Long id) {
        try {
            return userDao.findById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("No user with name: " + login);
        }
        Hibernate.initialize(user.getRoles());
        return user;
    }
}
