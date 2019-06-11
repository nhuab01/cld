package com.krkproduce.calendarlib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public static final String DATE_FORMAT_YYYY_MM_JAPANESE = "yyyy年 MM月";

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public static Date convertToDate(String source, String format) {
        if (source == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Date date = formatter.parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToString(Date source, String format) {
        if (source == null) return null;
        DateFormat df = new SimpleDateFormat(format);
        return df.format(source);
    }
}
