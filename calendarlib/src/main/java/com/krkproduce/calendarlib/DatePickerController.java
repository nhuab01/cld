package com.krkproduce.calendarlib;

import com.krkproduce.calendarlib.model.CalendarDay;
import com.krkproduce.calendarlib.model.SelectedDays;
import java.util.ArrayList;

public interface DatePickerController {

    void onDayOfMonthSelected(CalendarDay selectedDay);

    void onDaysSelected(ArrayList<CalendarDay> selectedDays);

    void onFirstDayOfMonthScrolled(CalendarDay firstDay);
}
