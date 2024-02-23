package za.co.ctrltab.tx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test","mock"})
class TransactioningApplicationTests {

    @Test
    void contextLoads() {
    }

}
