package com.company.financialmanagement.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String generateTimeStamp() {
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        return now.format(isoDateTime);
    }
}
