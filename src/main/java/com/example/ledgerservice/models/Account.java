package com.example.ledgerservice.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Account {

    @Id
    private UUID id;

    private BigDecimal balance;

    @Version
    private Long version;

    //Constructors
    public Account() {}

    public Account(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getVersion() {
        return version;
    }

    //Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
