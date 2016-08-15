package com.github.signal2564.magorarest.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/1/")
public class UserController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Environment env;

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody final UserLogin login, HttpServletResponse response) throws ServletException {
        try {
            UserDetails details = userDetailsService.loadUserByUsername(login.name);
            if (!details.getPassword().equals(login.password))
                throw new ServletException("Invalid login");

            return new LoginResponse(Jwts.builder().setSubject(login.name)
                    .claim("roles", details.getAuthorities()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, env.getProperty("jwt.secret")).compact());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    private static class UserLogin {
        public String name;
        public String password;
    }

    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}