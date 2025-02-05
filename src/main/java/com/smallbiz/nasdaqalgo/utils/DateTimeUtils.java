package com.smallbiz.nasdaqalgo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtils {

    static private DateTimeFormatter serverDateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    public static String nowInDisplay(){
        return dateFormat.format(new Date());
    }

    public static int today(){
        LocalDate today = LocalDate.now();
        return Integer.parseInt(today.format(serverDateFormatter));
    }

    public static int getServerDateAfterDays(int days){
        LocalDate today = LocalDate.now();
        LocalDate newDate = today.plusDays(days);
        return Integer.parseInt(newDate.format(serverDateFormatter));
    }

    public static int getServerDateBeforeDays(int days){
        LocalDate today = LocalDate.now();
        LocalDate newDate = today.minusDays(days);
        return Integer.parseInt(newDate.format(serverDateFormatter));
    }

    public static Timestamp getTimestampAfterDays(int days){

        long offset=days*24L*60L*60L*1000L;
        return new Timestamp( System.currentTimeMillis() + offset);
    }

    public static Timestamp getTimestampBeforeDays(int days){

        long offset=days*24L*60L*60L*1000L;
        return new Timestamp( System.currentTimeMillis() - offset);
    }

    // Gets the number of days remaining from today to the specified YYYYMMDD date
    public static long getRemainingDays(int serverDate) {

        if (serverDate == 0 ){
            return -1;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate targetDate = LocalDate.parse(String.valueOf(serverDate), formatter);
        LocalDate today = LocalDate.now();

        if (LocalDate.now().isAfter(targetDate)){
            return 0;
        }

        return ChronoUnit.DAYS.between(today, targetDate);
    }


    // Converts a timestamp (milliseconds since epoch) to YYYYMMDD format
    public static int timestampToServerDate(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(new Date(timestamp).toInstant(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return Integer.parseInt(dateTime.format(formatter));
    }
/*
    // Converts a YYYYMMDD formatted string to a timestamp (milliseconds since epoch)
    public static long serverDateToTimestamp(int serverDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(String.valueOf(serverDate), formatter);
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
*/

    // Converts a timestamp to a formatted string in "dd MMM, yyyy"
    public static String timestampToDisplay(long timestamp) {
        // Convert timestamp to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());

        // Define the formatter for "dd MMM, yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");

        // Format and return the date string
        return dateTime.format(formatter);
    }

    // Converts an integer in YYYYMMDD format to a formatted string in "dd MMM, yyyy"
    public static String serverDateToDisplay(int yyyymmdd) {
        // Extract the year, month, and day from the integer
        int year = yyyymmdd / 10000;
        int month = (yyyymmdd % 10000) / 100;
        int day = yyyymmdd % 100;

        // Create a LocalDate object using the extracted values
        LocalDate date = LocalDate.of(year, month, day);

        // Define the formatter for "dd MMM, yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");

        // Format and return the date string
        return date.format(formatter);
    }

}

