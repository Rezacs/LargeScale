package com.alibou.coding.mongodemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        return userService.registerUser(email, password);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }
}