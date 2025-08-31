package com.example.ledgerservice.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(indexes = @Index(columnList = "transferId"))
public class LedgerEntry {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID transferId;
    private UUID accountId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private EntryType type; // DEBIT or CREDIT
    private LocalDateTime createdAt;

    public LedgerEntry(UUID transferId, UUID fromId, BigDecimal amount, EntryType debit, LocalDateTime now) {
        this.transferId = transferId;
        this.accountId = fromId;
        this.amount = amount;
        this.type = debit;
        this.createdAt = now;
    }

    public LedgerEntry() {

    }
}

