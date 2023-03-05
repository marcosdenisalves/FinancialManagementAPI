package com.company.financialmanagement.exceptions.exceptionHandlers;

import org.springframework.http.ResponseEntity;

public class ResponseEntityExceptionHandler {
    public static ResponseEntity<Object> build(ErrorExceptionHandler errorExceptionHandler) {
        return new ResponseEntity<>(errorExceptionHandler, errorExceptionHandler.getStatus());
    }
}
