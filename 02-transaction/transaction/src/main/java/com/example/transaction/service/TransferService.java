package com.example.transaction.service;
import com.example.transaction.model.User;
import com.example.transaction.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    private UserRepository userRepo;
    @Transactional
    public void transferMoney(Long fromId, Long toId, Double amount) {
        User sender = userRepo.findById(fromId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepo.findById(toId).orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepo.save(sender);
        userRepo.save(receiver);
    }
}
