package za.co.ctrltab.tx.dto.mapper;

import lombok.experimental.UtilityClass;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.persistence.entity.Transaction;

@UtilityClass
public class TransactionMapper {
    public TransactionDto mapDto(final Transaction transaction) {
        return TransactionDto.builder()
                .account(transaction.getAccount())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .category(transaction.getCategory())
                .location(transaction.getLocation())
                .paymentMethod(transaction.getPaymentMethod())
                .transactionType(transaction.getTransactionType())
                .reference(transaction.getReference())
                .referenceNumber(transaction.getReferenceNumber())
                .currency(transaction.getCurrency())
                .storeName(transaction.getStoreName())
                .transactionTimestamp(transaction.getTransactionTimestamp())
                .build();
    }

    public Transaction mapEntity(final TransactionDto transactionDto) {
        return Transaction.builder()
                .account(transactionDto.account())
                .amount(transactionDto.amount())
                .description(transactionDto.description())
                .category(transactionDto.category())
                .location(transactionDto.location())
                .paymentMethod(transactionDto.paymentMethod())
                .transactionType(transactionDto.transactionType())
                .reference(transactionDto.reference())
                .referenceNumber(transactionDto.referenceNumber())
                .currency(transactionDto.currency())
                .storeName(transactionDto.storeName())
                .transactionTimestamp(transactionDto.transactionTimestamp())
                .build();
    }
}
