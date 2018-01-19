package com.dg.sigco.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UtilDate {
    public static final String _DATE_FORMAT_SHORT = "yyyy-MM-dd";
    public static final String DATE_ES = "dd 'de' MMM 'de' yyyy";

    public static Timestamp getCurrentDateTimestamp(){
        return Timestamp.valueOf(UtilDate.getCurrentDateString());
    }

    public static Timestamp getCurrentDateInitTimestamp(){
        return getTimestampInitDay(getCurrentDateString(_DATE_FORMAT_SHORT));
    }

    public static String getCurrentDateString(){
        return getCurrentDateString(_DATE_FORMAT_SHORT);
    }

    public static String getCurrentDateString(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date now = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-05"));
        String stringDate = sdf.format(now);
        return stringDate;
    }

    public static String getCurrentDateShort(){
        return getDateShort(new Date());
    }

    public static String getDateShort(Date date){
        return new SimpleDateFormat(_DATE_FORMAT_SHORT).format(date);
    }

    public static Timestamp getTimestampInitDay(String date){
        if(date.length() == 10){
            date = date+" 00:00:00";
        }
        if(date.length() == 16){
            date = date+":00";
        }
        return Timestamp.valueOf(date);
    }

    public static String getDateES(Date date){
        SimpleDateFormat formateador = new SimpleDateFormat(DATE_ES, new Locale("es"));
        return formateador.format(date);
    }

    public static Date stringToDate(String str, String format){
        DateFormat dFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return dFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateES(String date, String format){
        return getDateES(stringToDate(date, format));
    }

    public static String getDateES(long time){
        return getDateES(new Timestamp(time));
    }

    public static Date addDays(Date date, int numDays){
        long ltime = date.getTime() + numDays * 24 * 60 * 60 * 1000;
        return new Date(ltime);
    }

    public static String fillNumber(int n){
        return n < 10 ? "0"+n : String.valueOf(n);
    }
}
