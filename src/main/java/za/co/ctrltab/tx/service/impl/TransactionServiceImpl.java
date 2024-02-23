package za.co.ctrltab.tx.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.dto.mapper.TransactionMapper;
import za.co.ctrltab.tx.persistence.repository.TransactionRepository;
import za.co.ctrltab.tx.persistence.repository.UserRepository;
import za.co.ctrltab.tx.service.TransactionService;
import za.co.ctrltab.tx.service.exception.TransactioningException;

import java.util.Collection;

@Slf4j
@Service
@Profile("!mock")
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public void captureTransaction(final TransactionDto transaction, final String username) throws TransactioningException {
        userRepository.findByUsername(username).ifPresentOrElse(appUser -> {
            var tx = TransactionMapper.mapEntity(transaction);
            tx.setUser(appUser);
            transactionRepository.save(tx);
        }, () -> {
            throw new TransactioningException("User not registered.", HttpStatus.NOT_FOUND);
        });

    }

    @Override
    public Collection<TransactionDto> findAllTransactions(int pageSize, int limit) {
        transactionRepository.findAll(PageRequest.of(pageSize, limit));
        return transactionRepository.findAll(PageRequest.of(pageSize, limit))
                .map(TransactionMapper::mapDto).stream().toList();
    }
}
