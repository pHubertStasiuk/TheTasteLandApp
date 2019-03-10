package com.tasteland.app.Tasteland.config.auth;

import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Component
public class TastelandAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password =  authentication.getCredentials() == null ? "" : authentication.getCredentials().toString();
        Optional<User> user = userService.getUser(username);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("Invalid username!");
        }
        if(!encoder.matches(password,user.get().getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user.get(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
