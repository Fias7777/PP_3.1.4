package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveRoles(List<Role> roles) {
        entityManager.persist(roles);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> findByIdRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role",roles);
        return new ArrayList<>(q.getResultList());
    }

    @Override
    public List<Role> findRolesByNameIn(List<String> roleNames) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.roleName in :role", Role.class);
        q.setParameter("role",roleNames);
        return new ArrayList<>(q.getResultList());
    }
}
