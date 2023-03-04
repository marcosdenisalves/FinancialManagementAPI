package com.company.financialmanagement.service;

import com.company.financialmanagement.exception.exceptionHandler.ErrorExceptionHandler;
import com.company.financialmanagement.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


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
                .status(HttpStatus.UNAUTHORIZED)
                .message("Filter Exception")
                .errors(details).build();

        ObjectMapper mapper = new ObjectMapper();

        String jsonObject = mapper.writeValueAsString(err);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonObject);
    }
}
