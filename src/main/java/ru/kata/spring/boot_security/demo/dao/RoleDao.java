package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDao {
    void saveRoles(List<Role> roles);
    List<Role> getAllRoles();
    void addRole(Role role);
    Role findById(long id);
    List<Role> findByIdRoles(List<Long> roles);
}
