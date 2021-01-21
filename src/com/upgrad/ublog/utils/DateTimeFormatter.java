package com.upgrad.ublog.utils;


import java.time.LocalDateTime;

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
