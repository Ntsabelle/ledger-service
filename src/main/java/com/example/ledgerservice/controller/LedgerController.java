package com.example.ledgerservice.controller;

import com.example.ledgerservice.models.TransferRequest;
import com.example.ledgerservice.service.LedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        boolean success = ledgerService.applyTransfer(
                request.getTransferId(),
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );

        return success
                ? ResponseEntity.ok("Transfer applied")
                : ResponseEntity.status(409).body("Duplicate transfer or failure");
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "ledger-service");
        status.put("timestamp", java.time.LocalDateTime.now());
        return ResponseEntity.ok(status);
    }
}
