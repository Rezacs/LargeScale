package com.alibou.coding.mongodemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Configuration
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    @Lazy
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }

    public String registerUser(String email, String plainPassword) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User already exists!");
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(plainPassword));
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    public boolean signIn(String email, String password) {
        boolean flag = false;
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            flag = true;
        }

        return flag;
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }
        return user;
    }

}
