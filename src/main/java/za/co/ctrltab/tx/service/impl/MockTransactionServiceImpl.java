package za.co.ctrltab.tx.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.dto.mapper.TransactionMapper;
import za.co.ctrltab.tx.persistence.entity.Transaction;
import za.co.ctrltab.tx.service.TransactionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile("mock")
public class MockTransactionServiceImpl implements TransactionService {
    private static final Set<Transaction> transactions = new HashSet<>();
    @Override
    public void captureTransaction(final TransactionDto transaction, final String username) {
        log.info("Transaction Saved [{}]", transaction);
        transactions.add(TransactionMapper.mapEntity(transaction));
    }

    @Override
    public Collection<TransactionDto> findAllTransactions(final int pageSize, final int limit) {
        return transactions.stream().map(TransactionMapper::mapDto).collect(Collectors.toList());
    }
}
