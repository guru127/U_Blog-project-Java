package com.upgrad.ublog.utils;


import java.time.LocalDateTime;

/**
 * TODO: 4.13. Implement a method with the following signature.
 *  public static String format(LocalDateTime localDateTime)
 *  This method should convert the default date time to the human readable format[dd-MM-yyyy HH:mm:ss].
 */

public class DateTimeFormatter {
    public static String format(LocalDateTime localDteTime){
    LocalDateTime localDate = LocalDateTime.now();

        String dateTime= localDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        return dateTime;
    } public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(format(localDateTime));
    }
}
