package com.tasteland.app.Tasteland.service;

import com.tasteland.app.Tasteland.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String username);
    void save(User user);
}
