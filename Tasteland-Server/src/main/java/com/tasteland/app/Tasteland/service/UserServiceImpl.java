package com.tasteland.app.Tasteland.service;
import com.tasteland.app.Tasteland.model.Role;
import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.model.UserValidator;
import com.tasteland.app.Tasteland.repository.RoleDAO;
import com.tasteland.app.Tasteland.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public Optional<User> getUser(String username) {
        return userDAO.getUser(username);
    }
    @Transactional
    @Override
    public void save(UserValidator userValidator) {
        User user = new User();
        user.setUsername(userValidator.getUserName());
        user.setPassword(passwordEncoder.encode(userValidator.getPassword()));
        user.setFirstName(userValidator.getFirstName());
        user.setLastName(userValidator.getLastName());
        user.setEmail(userValidator.getEmail());
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findRoleByName("ROLE_USER"));
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Transactional
    @Override
    public Optional<User> getUserById(int theId) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public void deleteCustomer(int theId) {

    }


}
