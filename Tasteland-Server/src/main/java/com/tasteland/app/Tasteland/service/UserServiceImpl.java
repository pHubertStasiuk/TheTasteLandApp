package com.tasteland.app.Tasteland.service;


import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public Optional<User> getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }
}
