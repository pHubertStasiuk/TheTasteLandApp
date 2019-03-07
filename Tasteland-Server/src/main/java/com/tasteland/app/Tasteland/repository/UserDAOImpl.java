package com.tasteland.app.Tasteland.repository;

import com.tasteland.app.Tasteland.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@NamedQueries(value = {
        @NamedQuery(name = "getUserByUsername", query = "SELECT u FROM User u where u.userName=:uName"),
        @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u")}
)
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> getUser(String username) {

        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> theQuery = session.createNamedQuery("getUserByUsername", User.class);
        theQuery.setParameter("uName", username);
        Optional<User> theUser;
        try {
            theUser = Optional.of(theQuery.getSingleResult());
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;

    }
    @Override
    public void save(User user) {

        Session session = sessionFactory.getCurrentSession();
//        Query<User> theQuery = session.createQuery()

    }
}
