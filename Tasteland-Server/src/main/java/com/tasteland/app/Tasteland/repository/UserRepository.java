package com.tasteland.app.Tasteland.repository;


import com.tasteland.app.Tasteland.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);
}
