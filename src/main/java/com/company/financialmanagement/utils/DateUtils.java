package com.company.financialmanagement.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String DATE_FORMATTER= "dd-MM-yyyy HH:mm:ss";

    public static String generateTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return now.format(dateTimeFormatter);
    }
}
