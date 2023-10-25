package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void saveRoles(List<Role> roles) {
        roleDao.saveRoles(roles);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role findById(long id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findByIdRoles(List<Long> rolesId) {
        return roleDao.findByIdRoles(rolesId);
    }

    @Override
    public List<Role> findRolesByNameIn(List<String> roleNames) {
        return roleDao.findRolesByNameIn(roleNames);
    }
}
