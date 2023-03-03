package com.company.financialmanagement.exception.exceptionHandler;

import com.company.financialmanagement.exception.exceptionHandler.ErrorExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpiredTokenHandler {

    public ExpiredTokenHandler(@NonNull HttpServletResponse response, @NonNull ExpiredJwtException exception) throws IOException {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ErrorExceptionHandler err = ErrorExceptionHandler.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED)
                .message("Token is expired")
                .errors(details).build();

        ObjectMapper mapper = new ObjectMapper();

        String jsonObject = mapper.writeValueAsString(err);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonObject);
    }
}
