package io.tatagulov.goodproject.web.api;


import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Converter {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern( "dd.MM.yyyy HH:mm:ss");

    public static String getStringValue(Object object)  {
        if (object==null) return null;
        if (object instanceof String) return (String) object;
        if (object instanceof Timestamp) return timeStampFormatter.format(((Timestamp) object).toLocalDateTime());
        if (object instanceof Time) return timeFormatter.format(((Time) object).toLocalTime());
        if (object instanceof Date) return dateFormatter.format(((Date) object).toLocalDate());

        if (object instanceof Number) return object.toString();
        if (object instanceof Boolean) return object.toString();

        if (object instanceof Clob) {
            Clob clob = (Clob) object;
            try {
                return clob.getSubString(1, (int) clob.length());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
                
        String message = String.format("convert for class %s not implement", object.getClass().getName());
        throw new RuntimeException(message);
    }

    public static <T> T getObjectValue(String value,Class<T> type) {
        if (value==null || value.isEmpty()) return null;
        if (type == String.class) return (T) value;
        if (type == Date.class) return (T) Date.valueOf(LocalDate.parse(value,dateFormatter));
        if (type == Time.class) return (T) Time.valueOf(LocalTime.parse(value,timeFormatter));
        if (type == Timestamp.class) return (T) Timestamp.valueOf(LocalDateTime.parse(value,timeStampFormatter));

        if (type == Byte.class) return (T) Byte.valueOf(value);
        if (type == Short.class) return (T)Short.valueOf(value);
        if (type == Integer.class) return (T)(Integer)Double.valueOf(value).intValue();
        if (type == Long.class) return (T)Long.valueOf(value);
        if (type == Float.class) return (T)Float.valueOf(value);
        if (type == Double.class) return (T)Double.valueOf(value);
        if (type == BigDecimal.class) return (T)new BigDecimal(value);

        if (type == Boolean.class) return (T)Boolean.valueOf(value);
        if (type == byte[].class) return (T) DatatypeConverter.parseBase64Binary(value);

        String message = String.format("convert for class %s not implement",type.getName());
        throw new RuntimeException(message);
    }

    public static <T> T getObjectValue(Map<String, String> updateData, String paramName, Class<T> type) {
        String stringValue = updateData.get(paramName);
        return getObjectValue(stringValue,type);
    }
}
