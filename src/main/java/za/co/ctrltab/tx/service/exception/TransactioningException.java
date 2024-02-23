package za.co.ctrltab.tx.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TransactioningException extends RuntimeException {
    private String message;
    private HttpStatus status;
}
