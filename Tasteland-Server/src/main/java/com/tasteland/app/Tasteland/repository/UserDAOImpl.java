package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override

    public Optional<User> getUser(String username) {

        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.getNamedQuery("getUserByUsername");
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
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public Optional<User> getUserById(int theId) {

        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.getNamedQuery("getUserById");
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
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.getNamedQuery("deleteUserById");
        theQuery.setParameter("uId", theId);
        theQuery.executeUpdate();
    }
}
