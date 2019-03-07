package com.tasteland.app.Tasteland.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasteland.app.Tasteland.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CredentialsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = this.getUserNamePasswordAuthenticationToken(request);
        } catch (IOException e) {
        }
        setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    private UsernamePasswordAuthenticationToken getUserNamePasswordAuthenticationToken(HttpServletRequest request) throws IOException {

        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        String content;
        User user;
        try {
            bufferedReader = request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                sb.append(charBuffer, 0, bytesRead);
            }
            content = sb.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                user = objectMapper.readValue(content, User.class);
            } catch (Throwable t) {
                throw new IOException(t.getMessage(), t);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    }
}


