package com.example.transaction.controller;

import com.example.transaction.model.User;
import com.example.transaction.repository.UserRepository;
import com.example.transaction.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransferService transferService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam Double amount) {
        transferService.transferMoney(from, to, amount);
        return "Transfer successful!";
    }
}

