package za.co.ctrltab.tx.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import za.co.ctrltab.tx.dto.TransactionDto;
import za.co.ctrltab.tx.service.TransactionService;
import za.co.ctrltab.tx.service.exception.TransactioningException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    @DisplayName("\uD83D\uDE2D Expecting Failure")
    void testTxService_noUserFound_expect_exception() {
        var exception = assertThrows(TransactioningException.class,
                () -> transactionService.captureTransaction(TransactionDto.builder().build(), "username"));
        assertEquals("User not registered.", exception.getMessage());
    }
}