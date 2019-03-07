package com.tasteland.app.Tasteland.repository;


import com.tasteland.app.Tasteland.model.User;

import java.util.Optional;

public interface UserDAO {

    Optional<User> getUser(String username);
    void save(User user);

}
