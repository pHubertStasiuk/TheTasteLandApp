package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private EntityManagerFactory managerFactory;

    @Override
    public Role findRoleByName(String roleName) {

        EntityManager manager = managerFactory.createEntityManager();
        TypedQuery<Role> query = manager.createQuery("SELECT r FROM Role r WHERE name=:roleName", Role.class);
        query.setParameter("roleName", roleName);
        Role role;
        try {
            role = query.getSingleResult();
        } catch (Exception ex) {
            role = null;
        }
        return role;
    }
}
