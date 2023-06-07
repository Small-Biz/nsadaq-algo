package com.smallbiz.nasdaqalgo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateTimeHelper {

    public static int todayInServerDate(){
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime localDateTime = LocalDateTime.now();
        return Integer.parseInt( customFormatter.format( localDateTime ) );
    }

    public static int nowInServerTime(){
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("hhmm");
        LocalDateTime localDateTime = LocalDateTime.now();
        return Integer.parseInt( customFormatter.format( localDateTime ) );
    }
    
}
