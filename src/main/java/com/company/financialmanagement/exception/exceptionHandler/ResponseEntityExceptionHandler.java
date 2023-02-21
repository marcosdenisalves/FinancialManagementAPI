package com.company.financialmanagement.exception.exceptionHandler;

import org.springframework.http.ResponseEntity;

public class ResponseEntityExceptionHandler {
    public static ResponseEntity<Object> build(ErrorExceptionHandler errorExceptionHandler) {
        return new ResponseEntity<>(errorExceptionHandler, errorExceptionHandler.getStatus());
    }
}
