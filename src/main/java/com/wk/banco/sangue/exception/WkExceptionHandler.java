package com.wk.banco.sangue.exception;


import com.wk.banco.sangue.domain.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WkExceptionHandler {

    @ExceptionHandler(value = WkExceptionCustomer.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomerLoginException(WkExceptionCustomer ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()) {
        };
    }

}
