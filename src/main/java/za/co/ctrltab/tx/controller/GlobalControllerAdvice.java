package za.co.ctrltab.tx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.co.ctrltab.tx.service.exception.TransactioningException;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(TransactioningException.class)
    public ProblemDetail resourceNotFoundException(TransactioningException ex) {
        return  ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
