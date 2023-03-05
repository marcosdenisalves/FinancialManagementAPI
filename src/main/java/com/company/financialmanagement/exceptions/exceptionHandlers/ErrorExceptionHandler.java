package com.company.financialmanagement.exceptions.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorExceptionHandler {
    private String timeStamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
