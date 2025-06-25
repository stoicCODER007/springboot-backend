package com.example.jwtsecuritydemo.repository;

import com.example.jwtsecuritydemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
