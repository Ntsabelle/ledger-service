package com.example.ledgerservice.repository;

import com.example.ledgerservice.models.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, UUID> {
    boolean existsByTransferId(UUID transferId);
}

