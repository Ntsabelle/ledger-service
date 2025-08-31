package com.example.ledgerservice.service;

import com.example.ledgerservice.models.Account;
import com.example.ledgerservice.models.EntryType;
import com.example.ledgerservice.models.LedgerEntry;
import com.example.ledgerservice.repository.AccountRepository;
import com.example.ledgerservice.repository.LedgerEntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LedgerService {

    private final AccountRepository accountRepo;
    private final LedgerEntryRepository ledgerRepo;

    public LedgerService(AccountRepository accountRepo, LedgerEntryRepository ledgerRepo) {
        this.accountRepo = accountRepo;
        this.ledgerRepo = ledgerRepo;
    }

    @Transactional
    public boolean applyTransfer(UUID transferId, UUID fromId, UUID toId, BigDecimal amount) {
        boolean alreadyProcessed = ledgerRepo.existsByTransferId(transferId);
        if (alreadyProcessed) {
            return true; // skip re-processing
        }

        Account from = accountRepo.findById(fromId)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account to = accountRepo.findById(toId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepo.save(from);
        accountRepo.save(to);

        ledgerRepo.save(new LedgerEntry(transferId, fromId, amount, EntryType.DEBIT, LocalDateTime.now()));
        ledgerRepo.save(new LedgerEntry(transferId, toId, amount, EntryType.CREDIT, LocalDateTime.now()));

        return true;
    }
}

