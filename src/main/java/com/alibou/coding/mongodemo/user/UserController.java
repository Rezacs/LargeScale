package com.alibou.coding.mongodemo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.signUp(user));
    }

    @GetMapping("/signin/{Email}/{Password}")
    public ResponseEntity<Boolean> signIn(@PathVariable String Email, @PathVariable String Password) {
        return ResponseEntity.ok(userService.signIn(Email, Password));
    }
}
