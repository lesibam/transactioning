package za.co.ctrltab.tx.service;

import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.service.exception.TransactioningException;

import java.util.Collection;

public interface TransactionService {
    void captureTransaction(TransactionDto transaction, String username) throws TransactioningException;
    Collection<TransactionDto> findAllTransactions(int page, int limit);
}
