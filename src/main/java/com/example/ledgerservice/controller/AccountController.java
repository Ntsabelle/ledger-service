package com.example.ledgerservice.controller;

import com.example.ledgerservice.models.Account;
import com.example.ledgerservice.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepo;

    public AccountController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    // Create account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam BigDecimal initialBalance) {
        Account account = new Account(UUID.randomUUID(), initialBalance);
        accountRepo.save(account);
        return ResponseEntity.ok(account);
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
        Optional<Account> account = accountRepo.findById(id);
        return account.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

