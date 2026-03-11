package com.jwt.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.entity.User;
import com.jwt.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public Optional<User> login(String email,String password) {

        Optional<User> user = repo.findByEmail(email);

        if(user.isPresent() &&
           encoder.matches(password,user.get().getPassword())) {

            return user;
        }

        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email) {

        return repo.findByEmail(email);
    }

}