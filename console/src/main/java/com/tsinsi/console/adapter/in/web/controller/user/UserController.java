package com.tsinsi.console.adapter.in.web.controller.user;

import com.tsinsi.console.application.in.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser() {
        return ResponseEntity.ok(userService.createUser("", ""));
    }
}
