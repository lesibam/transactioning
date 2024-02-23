package za.co.ctrltab.tx.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto (LocalDateTime transactionTimestamp, BigDecimal amount, String description, String account, TransactionType transactionType, Category category, String paymentMethod, String storeName, String location, String referenceNumber, String currency, String reference, Long userId) {
    @Builder public TransactionDto {}
}
