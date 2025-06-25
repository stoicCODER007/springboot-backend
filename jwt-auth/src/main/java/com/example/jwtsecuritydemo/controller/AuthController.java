package com.example.jwtsecuritydemo.controller;
import com.example.jwtsecuritydemo.dto.LoginRequest;
import com.example.jwtsecuritydemo.dto.LoginResponse;
import com.example.jwtsecuritydemo.dto.RegisterRequest;
import com.example.jwtsecuritydemo.model.User;
import com.example.jwtsecuritydemo.security.JwtUtil;
import com.example.jwtsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired private UserService userService;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // 1. Authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        );
        authManager.authenticate(authentication); // throws error if invalid

        // 2. Load user details
        User user = (User) userDetailsService.loadUserByUsername(request.getUsername());

        // 3. Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // 4. Return token
        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
        return "✅ Registered user: " + user.getUsername();
    }
}
