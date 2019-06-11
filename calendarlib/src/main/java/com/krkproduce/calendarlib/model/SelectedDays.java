package com.krkproduce.calendarlib.model;

import java.util.ArrayList;

public class SelectedDays {
    private ArrayList<CalendarDay> mCalendarList;

    public SelectedDays() {
        mCalendarList = new ArrayList<>();
    }

    public ArrayList<CalendarDay> getDaySelectedList() {
        return mCalendarList;
    }

    public void setDaySelectedList(ArrayList<CalendarDay> calendarList) {
        mCalendarList = calendarList;
    }

    public void addDaySelected(CalendarDay daySelected) {
        if (!mCalendarList.contains(daySelected)) {
            mCalendarList.add(daySelected);
        }
    }

    public void removeDaySelected(CalendarDay daySelected) {
        mCalendarList.remove(daySelected);
    }

    public boolean isContains(CalendarDay daySelected){
        return mCalendarList.contains(daySelected);
    }

    public void clearDaySelectedList() {
        mCalendarList.clear();
    }
}
