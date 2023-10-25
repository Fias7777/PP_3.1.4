package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    void saveRoles(List<Role> roles);

    public void addRole(Role role);
    public Role findById(long id);

    List<Role> findByIdRoles(List<Long> rolesId);

    List<Role> findRolesByNameIn(List<String> roleNames);
}
