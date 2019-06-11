package com.krkproduce.calendarlib.model;

public class Event {
    private int mId;
    private CalendarDay mDate;
    private String mStatus;
    private boolean mHasReservation;

    public Event(int id, CalendarDay date, String status, boolean hasReservation) {
        mId = id;
        mDate = date;
        mStatus = status;
        mHasReservation = hasReservation;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public CalendarDay getDate() {
        return mDate;
    }

    public void setDate(CalendarDay date) {
        mDate = date;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public boolean isHasReservation() {
        return mHasReservation;
    }

    public void setHasReservation(boolean hasReservation) {
        mHasReservation = hasReservation;
    }

    @Override
    public String toString() {
        return "Event{"
                + "mDate="
                + mDate
                + ", mStatus='"
                + mStatus
                + '\''
                + ", mHasReservation="
                + mHasReservation
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (mId != event.mId) return false;
        return mDate != null ? mDate.equals(event.mDate) : event.mDate == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        return result;
    }
}
