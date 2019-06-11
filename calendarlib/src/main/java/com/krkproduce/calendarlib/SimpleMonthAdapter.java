package com.krkproduce.calendarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.krkproduce.calendarlib.model.CalendarDay;
import com.krkproduce.calendarlib.model.Event;
import com.krkproduce.calendarlib.model.SelectedDays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SimpleMonthAdapter extends RecyclerView.Adapter<SimpleMonthAdapter.ViewHolder>
        implements OnDayClickListener {

    protected static final int MONTHS_IN_YEAR = 12;
    private static final int MAX_YEARS = 2050;
    private final TypedArray typedArray;
    private final Context mContext;
    private final DatePickerController mController;
    private final Calendar calendar;
    private final Integer firstMonth;
    private final Integer lastMonth;
    private ArrayList<Event> mEvents;

    private SelectedDays mSelectedDays;
    private boolean isEnableMultiDaySelect;

    public SimpleMonthAdapter(Context context, DatePickerController datePickerController,
            TypedArray typedArray) {
        this.typedArray = typedArray;
        calendar = Calendar.getInstance();
        firstMonth = typedArray.getInt(R.styleable.DatePickerView_firstMonth,
                calendar.get(Calendar.MONTH));
        lastMonth = typedArray.getInt(R.styleable.DatePickerView_lastMonth,
                (calendar.get(Calendar.MONTH) - 1) % MONTHS_IN_YEAR);
        mSelectedDays = new SelectedDays();
        mContext = context;
        mController = datePickerController;
        mEvents = new ArrayList<Event>();
        init();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final SimpleMonthView simpleMonthView = new SimpleMonthView(mContext, typedArray);
        return new ViewHolder(simpleMonthView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final SimpleMonthView monthView = viewHolder.simpleMonthView;
        final HashMap<String, Integer> drawingParams = new HashMap<String, Integer>();
        int month;
        int year;

        month = (firstMonth + (position % MONTHS_IN_YEAR)) % MONTHS_IN_YEAR;
        year = position / MONTHS_IN_YEAR + calendar.get(Calendar.YEAR) + ((firstMonth + (position
                % MONTHS_IN_YEAR)) / MONTHS_IN_YEAR);

        monthView.reuse();

        drawingParams.put(SimpleMonthView.VIEW_PARAMS_YEAR, year);
        drawingParams.put(SimpleMonthView.VIEW_PARAMS_MONTH, month);
        drawingParams.put(SimpleMonthView.VIEW_PARAMS_WEEK_START, calendar.getFirstDayOfWeek());
        monthView.setMonthParams(drawingParams);

        monthView.setEvents(mEvents);
        monthView.setIsMultiSelectEnabled(isEnableMultiDaySelect);
        monthView.setSelectedDayList(mSelectedDays.getDaySelectedList());
        monthView.invalidate();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        int itemCount = (((MAX_YEARS - calendar.get(Calendar.YEAR)) + 1) * MONTHS_IN_YEAR);

        if (firstMonth != -1) {
            itemCount -= firstMonth;
        }

        if (lastMonth != -1) {
            itemCount -= (MONTHS_IN_YEAR - lastMonth) - 1;
        }

        return itemCount;
    }

    protected void init() {
        if (typedArray.getBoolean(R.styleable.DatePickerView_currentDaySelected, false)) {
            onDayTapped(new CalendarDay(System.currentTimeMillis()));
        }
        isEnableMultiDaySelect =
                typedArray.getBoolean(R.styleable.DatePickerView_enableMultiSelect, false);
    }

    @Override
    public void onDayClick(SimpleMonthView simpleMonthView, CalendarDay calendarDay) {
        if (calendarDay != null) {
            onDayTapped(calendarDay);
        }
    }

    protected void onDayTapped(CalendarDay calendarDay) {
        setSelectedDay(calendarDay);
    }

    public void setSelectedDay(CalendarDay calendarDay) {
        if (isEnableMultiDaySelect) {
            if (mSelectedDays.isContains(calendarDay)) {
                mSelectedDays.removeDaySelected(calendarDay);
            } else {
                mSelectedDays.addDaySelected(calendarDay);
            }
            mController.onDaysSelected(mSelectedDays.getDaySelectedList());
        } else {
            if (mSelectedDays.isContains(calendarDay)) {
                mSelectedDays.removeDaySelected(calendarDay);
                mController.onDayOfMonthSelected(null);
            } else {
                mSelectedDays.clearDaySelectedList();
                mSelectedDays.addDaySelected(calendarDay);
                mController.onDayOfMonthSelected(calendarDay);
            }
        }

        notifyDataSetChanged();
    }

    public void setEvents(ArrayList<Event> events) {
        if (events != null && events.size() > 0) {
            if (mEvents.size() == 0) {
                mEvents.addAll(events);
            } else {
                mEvents.removeAll(events);
                mEvents.addAll(events);
            }
            notifyDataSetChanged();
        }
    }

    public void setEnableMultiDaySelect(boolean isEnable) {
        if (isEnableMultiDaySelect) {
            mSelectedDays.clearDaySelectedList();
        }
        isEnableMultiDaySelect = isEnable;
        notifyDataSetChanged();
    }

    public ArrayList<CalendarDay> getSelectedDayList() {
        return mSelectedDays.getDaySelectedList();
    }

    public void removeAllSelectedDays() {
        mSelectedDays.clearDaySelectedList();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final SimpleMonthView simpleMonthView;

        public ViewHolder(View itemView, OnDayClickListener onDayClickListener) {
            super(itemView);
            simpleMonthView = (SimpleMonthView) itemView;
            simpleMonthView.setLayoutParams(
                    new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
            simpleMonthView.setClickable(true);
            simpleMonthView.setOnDayClickListener(onDayClickListener);
        }
    }
}
