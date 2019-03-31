package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<User> getUser(String username) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("getUserByUsername", User.class);
        query.setParameter("uName", username);
        Optional<User> theUser;
        try {
            theUser = Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            theUser = Optional.empty();
        }
        return theUser;

    }

    @Override
    public void save(User user) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(user);
        transaction.commit();

    }

    @Override
    public Optional<User> getUserById(int theId) {

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("getUserById", User.class);
        query.setParameter("uId", theId);
        Optional<User> theUser;
        try {
            theUser = Optional.ofNullable(query.getSingleResult());
        } catch (Exception ex) {
            theUser = Optional.empty();
        }
        return theUser;
    }

    @Override
    public void deleteUser(int theId) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("deleteUserById", User.class);
        query.setParameter("uId", theId);
        query.executeUpdate();
    }
}
