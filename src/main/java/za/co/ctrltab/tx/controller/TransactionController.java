package za.co.ctrltab.tx.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.persistence.entity.Transaction;
import za.co.ctrltab.tx.service.TransactionService;
import za.co.ctrltab.tx.service.exception.TransactioningException;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/${app.version:v1}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity captureTransaction(@RequestBody final TransactionDto transaction, Principal principal) throws TransactioningException {
        transactionService.captureTransaction(transaction, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = { "page", "size" })
    public ResponseEntity findAllTransaction(@RequestParam(value = "page", required = false) int page, @RequestParam(value = "size", required = false, defaultValue = "50") int size) {
        return ResponseEntity.ofNullable(transactionService.findAllTransactions(page, size));
    }
}
