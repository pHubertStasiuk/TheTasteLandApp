package com.tasteland.app.Tasteland.service;

import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.model.UserValidator;


import java.util.Optional;

public interface UserService  {

    User getUserByUsername(String username);
    void save(UserValidator user);
    User getUserById(Long theId);
    void deleteUser(Long theId);
}
