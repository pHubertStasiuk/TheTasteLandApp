package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String roleName) {

        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("SELECT r FROM Role r WHERE name=:roleName", Role.class);
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
