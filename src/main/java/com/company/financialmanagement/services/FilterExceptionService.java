package com.company.financialmanagement.services;

import com.company.financialmanagement.exceptions.exceptionHandlers.ErrorExceptionHandler;
import com.company.financialmanagement.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.http.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class FilterExceptionService {

    public void generateFilterException(HttpServletResponse response, Exception exception) throws IOException {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ErrorExceptionHandler err = ErrorExceptionHandler.builder()
                .timeStamp(DateUtils.generateTimeStamp())
                .message(getExceptionMessage(exception))
                .status(HttpStatus.UNAUTHORIZED)
                .errors(details).build();

        ObjectMapper mapper = new ObjectMapper();

        String jsonObject = mapper.writeValueAsString(err);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonObject);
    }

    private String getExceptionMessage(Exception exception) {
        if (exception instanceof ExpiredJwtException)
            return "Expired Token, please try to authenticate again";

        else if (exception instanceof MalformedJwtException)
            return "Invalid token, please check if this is a valid token";

        return "";
    }
}
