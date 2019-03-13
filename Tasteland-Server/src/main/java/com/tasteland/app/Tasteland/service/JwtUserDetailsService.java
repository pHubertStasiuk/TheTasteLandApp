package com.tasteland.app.Tasteland.service;

import com.tasteland.app.Tasteland.config.auth.JwtUserFactory;
import com.tasteland.app.Tasteland.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user = userService.getUser(username);

            if(!user.isPresent()) {
                throw new UsernameNotFoundException(String.format("Invalid username '%s'.", username));
            } else {
                return JwtUserFactory.create(user.get());
            }
    }
}
