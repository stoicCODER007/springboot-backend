package com.example.jwtsecuritydemo.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/public")
    public String publicApi() {
        return "🚀 This is a PUBLIC API. No token required.";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userApi() {
        return "👤 Hello USER! You are authenticated.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminApi() {
        return "🛡️ Welcome ADMIN! This is a protected ADMIN route.";
    }
}
