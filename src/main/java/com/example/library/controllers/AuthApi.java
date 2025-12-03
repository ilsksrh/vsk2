package com.example.library.controllers;

import com.example.library.models.AppUser;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        userService.register(user, passwordEncoder);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/test")
    public String test() {
        return "Security works!";
    }
}