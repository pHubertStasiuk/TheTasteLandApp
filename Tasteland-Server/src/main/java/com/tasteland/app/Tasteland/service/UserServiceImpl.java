package com.tasteland.app.Tasteland.service;

import com.tasteland.app.Tasteland.model.Role;
import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.model.UserValidator;
import com.tasteland.app.Tasteland.repository.RoleRepository;
import com.tasteland.app.Tasteland.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Transactional
    @Override
    public void save(UserValidator userValidator) {
        User user = new User();
        user.setUsername(userValidator.getUserName());
        user.setPassword(passwordEncoder.encode(userValidator.getPassword()));
        user.setFirstName(userValidator.getFirstName());
        user.setLastName(userValidator.getLastName());
        user.setPictureUrl(userValidator.getPictureUrl());
        user.setDateOfBirth(userValidator.getDateOfBirth());
        user.setGender(userValidator.getGender());
        user.setCountry(userValidator.getCountry());
        user.setEmail(userValidator.getEmail());
        user.setAccountCreated(new Date());
        user.setLastPasswordResetDate(new Date());
        user.setEnabled(true);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public User getUserById(Long theId) {
        return userRepository.getOne(theId);
    }
    @Transactional
    @Override
    public void deleteUser(Long theId) {
        userRepository.deleteById(theId);
    }


}
