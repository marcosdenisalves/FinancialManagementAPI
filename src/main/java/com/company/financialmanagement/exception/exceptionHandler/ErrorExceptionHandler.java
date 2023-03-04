package com.company.financialmanagement.exception.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
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
