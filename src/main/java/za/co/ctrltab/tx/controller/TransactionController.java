package za.co.ctrltab.tx.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.service.TransactionService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity captureTransaction(@RequestBody final TransactionDto transaction, Principal principal) {
        transactionService.captureTransaction(transaction, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity findAllTransaction(@RequestParam(required = false) int page, @RequestParam(required = false, defaultValue = "50") int size) {
        return ResponseEntity.ofNullable(transactionService.findAllTransactions(page, size));
    }
}
