package com.jwt.controller;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jwt.entity.User;
import com.jwt.security.JwtUtil;
import com.jwt.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    JwtUtil jwt = new JwtUtil();

    @PostMapping("/register")
    public User register(@RequestBody User user){

        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User user){

        Optional<User> u = service.login(user.getEmail(), user.getPassword());

        if(u.isPresent()){

            String token = jwt.generateToken(u.get().getEmail());

            return Map.of("token",token);
        }

        throw new RuntimeException("Invalid Credentials");
    }

    @GetMapping("/profile")
    public User profile(@RequestHeader("Authorization") String header){

        String token = header.substring(7);

        String email = jwt.extractEmail(token);

        return service.getUserByEmail(email).get();
    }

}