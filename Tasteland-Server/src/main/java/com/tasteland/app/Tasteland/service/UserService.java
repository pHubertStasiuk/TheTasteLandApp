package com.tasteland.app.Tasteland.service;

import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.model.UserValidator;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String username);
    void save(UserValidator user);
    Optional<User> getUserById(int theId);
    void deleteCustomer(int theId);

}
