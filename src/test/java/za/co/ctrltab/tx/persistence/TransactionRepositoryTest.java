package za.co.ctrltab.tx.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import za.co.ctrltab.tx.dto.Category;
import za.co.ctrltab.tx.dto.TransactionType;
import za.co.ctrltab.tx.persistence.entity.AppUser;
import za.co.ctrltab.tx.persistence.entity.Transaction;
import za.co.ctrltab.tx.persistence.repository.TransactionRepository;
import za.co.ctrltab.tx.persistence.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSave() {
        AppUser user = userRepository.save(AppUser.builder()
                .locked(false).username("username")
                        .password("securepassword")
                        .disabled(false)
                .build());
        var tx = Transaction.builder()
                .user(user)
                .transactionTimestamp(LocalDateTime.now())
                .storeName("Boxer Store")
                .currency("ZAR")
                .referenceNumber("ZARBX10001")
                .transactionType(TransactionType.DEBIT)
                .paymentMethod("CREDIT_CARD")
                .account("")
                .category(Category.EMOTIONAL_CARE)
                .description("Food")
                .location("Pretoria")
                .reference("Refund")
                .amount(BigDecimal.valueOf(500))
                .build();

        var savedTx = transactionRepository.save(tx);
        var found = transactionRepository.findById(savedTx.getId()).orElseThrow();

        assertEquals(savedTx.getId(), found.getId());
        assertEquals(savedTx.getStoreName(), found.getStoreName());
    }
}
