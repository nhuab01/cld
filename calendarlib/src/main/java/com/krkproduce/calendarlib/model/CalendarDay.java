package com.krkproduce.calendarlib.model;

import java.util.Calendar;
import java.util.Date;

public class CalendarDay {
    private Calendar calendar;
    public int day;
    public int month;
    public int year;

    public CalendarDay() {
        setTime(System.currentTimeMillis());
    }

    public CalendarDay(int year, int month, int day) {
        setDay(year, month, day);
    }

    public CalendarDay(long timeInMillis) {
        setTime(timeInMillis);
    }

    public CalendarDay(Calendar calendar) {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void setTime(long timeInMillis) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        calendar.setTimeInMillis(timeInMillis);
        month = this.calendar.get(Calendar.MONTH);
        year = this.calendar.get(Calendar.YEAR);
        day = this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void set(CalendarDay calendarDay) {
        year = calendarDay.year;
        month = calendarDay.month;
        day = calendarDay.day;
    }

    public void setDay(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date getDate() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarDay that = (CalendarDay) o;

        if (day != that.day) return false;
        if (month != that.month) return false;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ year: ");
        stringBuilder.append(year);
        stringBuilder.append(", month: ");
        stringBuilder.append(month);
        stringBuilder.append(", day: ");
        stringBuilder.append(day);
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }

    public boolean isAfter(CalendarDay calendarDay) {
        if (year > calendarDay.year) {
            return true;
        } else if (year == calendarDay.year && month > calendarDay.month) {
            return true;
        } else if (year == calendarDay.year
                && month == calendarDay.month
                && day >= calendarDay.day) {
            return true;
        }
        return false;
    }
}

