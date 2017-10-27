package io.tatagulov.badproject.web.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    public static Date dateParse(String date) {
        return Date.valueOf(LocalDate.parse(date,DATE_TIME_FORMATTER));
    }

    public static void validateNull(Object object,String objectName) {
        if (object==null) {
            String msg = String.format("field %s is null",objectName);
            throw new RuntimeException(msg);
        }
    }
}
