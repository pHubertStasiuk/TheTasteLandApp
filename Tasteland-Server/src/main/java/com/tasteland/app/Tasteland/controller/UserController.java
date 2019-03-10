package com.tasteland.app.Tasteland.controller;


import com.tasteland.app.Tasteland.config.auth.TastelandAuthenticationProvider;
import com.tasteland.app.Tasteland.model.ExecutionStatus;
import com.tasteland.app.Tasteland.model.User;
import com.tasteland.app.Tasteland.model.UserValidator;
import com.tasteland.app.Tasteland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TastelandAuthenticationProvider authenticationProvider;
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initUserRequest(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ExecutionStatus> processLogin(@RequestBody User reqUser) {

        Authentication authentication;
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(reqUser.getUsername(), reqUser.getPassword());
        try {
            authentication = authenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = new User();
            user.setUsername(authentication.getPrincipal().toString());
            user.setPassword(null);
            return new ResponseEntity<>(new ExecutionStatus("USER_LOGIN_SUCCESS", "Login Successful!", user),HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ExecutionStatus("USER_LOGIN_FAILED", "Login Unsuccessful!"),HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    public ResponseEntity<ExecutionStatus>  processLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return new ResponseEntity<>(new ExecutionStatus("USER_LOGOUT_SUCCESS", "User logout successful!"),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ExecutionStatus>  processRegistrationForm(
            @Valid @RequestBody UserValidator user,
            BindingResult theBindingResult) {
        String userName = user.getUserName();
        logger.info("Processing registration form for: " + userName);
        if (theBindingResult.hasErrors()) {
            return new ResponseEntity<>(new ExecutionStatus("USER_REGISTRATION_FAILED", "User registered successful!"), HttpStatus.EXPECTATION_FAILED);
        }
        Optional<User> existing = userService.getUser(userName);
        if (existing != null){
            logger.warning("User name already exists.");
            return new ResponseEntity<>(new ExecutionStatus("USER_REGISTRATION_FAILED", "User name already exists"),HttpStatus.EXPECTATION_FAILED);
        }
        // create user account
        userService.save(user);
        logger.info("Successfully created user: " + userName);

        return new ResponseEntity<>(new ExecutionStatus("USER_REGISTRATION_SUCCESS", "User registered successful!"),HttpStatus.CREATED);
    }
}
