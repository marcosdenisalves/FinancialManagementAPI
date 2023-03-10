package com.company.financialmanagement.exceptions.exceptionHandlers;

import com.company.financialmanagement.exceptions.AlreadyRegisteredUseException;
import com.company.financialmanagement.exceptions.NotFoundException;
import com.company.financialmanagement.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyRegisteredUseException.class)
    public ResponseEntity<Object> alreadyRegisteredUserExceptionHandler(AlreadyRegisteredUseException exception) {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ErrorExceptionHandler err = ErrorExceptionHandler.builder()
                .timeStamp(DateUtils.generateTimeStamp())
                .status(HttpStatus.OK)
                .message("User already is registered")
                .errors(details).build();

        return ResponseEntityExceptionHandler.build(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException exception) {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ErrorExceptionHandler err = ErrorExceptionHandler.builder()
                .timeStamp(DateUtils.generateTimeStamp())
                .status(HttpStatus.NOT_FOUND)
                .message("Object not found")
                .errors(details).build();

        return ResponseEntityExceptionHandler.build(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialsExceptionHandler(BadCredentialsException exception) {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ErrorExceptionHandler err = ErrorExceptionHandler.builder()
                .timeStamp(DateUtils.generateTimeStamp())
                .status(HttpStatus.UNAUTHORIZED)
                .message("User not registered")
                .errors(details).build();

        return ResponseEntityExceptionHandler.build(err);
    }
}
