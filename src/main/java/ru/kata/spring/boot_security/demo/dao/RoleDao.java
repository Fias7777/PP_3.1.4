package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDao {
    void saveRoles(List<Role> roles);
    List<Role> getAllRoles();
    void addRole(Role role);
    Role findById(int id);
    List<Role> findByIdRoles(List<Integer> roles);
    List<Role> findRolesByNameIn(List<String> name);
}
