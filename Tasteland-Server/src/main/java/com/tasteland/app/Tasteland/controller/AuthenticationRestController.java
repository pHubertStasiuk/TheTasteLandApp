package com.tasteland.app.Tasteland.controller;

import com.tasteland.app.Tasteland.config.auth.JwtUtils;
import com.tasteland.app.Tasteland.model.ExecutionStatus;
import com.tasteland.app.Tasteland.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User reqUser) throws AuthenticationException {
        authenticate(reqUser.getUsername(),reqUser.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(reqUser.getUsername());
        final String token = jwtUtils.generateToken(userDetails);
        return new ResponseEntity<>(new ExecutionStatus("ACCESS_TOKEN", "Access token has been issued.", token), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = (User) userDetailsService.loadUserByUsername(username);
        if (jwtUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtUtils.refreshToken(token);
            return new ResponseEntity<>(new ExecutionStatus("TOKEN_REFRESHED", "Access token has been refreshed.", refreshedToken), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ExecutionStatus("TOKEN_NOT_REFRESHED", "Access token has not been refreshed."), HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password!");
        }
    }
}
