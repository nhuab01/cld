package com.krkproduce.calendarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.krkproduce.calendarlib.model.CalendarDay;
import com.krkproduce.calendarlib.model.Event;
import java.util.ArrayList;

public class DayPickerView extends RecyclerView {

    protected Context mContext;
    protected SimpleMonthAdapter mAdapter;
    private DatePickerController mController;
    private TypedArray typedArray;
    private OnScrollListener onScrollListener;

    public DayPickerView(Context context) {
        this(context, null);
        init(context);
    }

    public DayPickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public DayPickerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (!isInEditMode()) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.DatePickerView);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            init(context);
        }
    }

    public void setController(DatePickerController controller) {
        this.mController = controller;
        setUpAdapter();
        setAdapter(mAdapter);
    }

    public void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context));
        mContext = context;
        setUpListView();

        onScrollListener = new OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final SimpleMonthView child = (SimpleMonthView) recyclerView.getChildAt(0);
                if (child == null) {
                    return;
                }
                if (mController != null) {
                    mController.onFirstDayOfMonthScrolled(child.getFirstDayOfMonth());
                }
            }
        };
    }

    protected void setUpAdapter() {
        if (mAdapter == null) {
            mAdapter = new SimpleMonthAdapter(getContext(), mController, typedArray);
        }
        mAdapter.notifyDataSetChanged();
    }

    protected void setUpListView() {
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(onScrollListener);
        setFadingEdgeLength(0);
    }

    public ArrayList<CalendarDay> getSelectedDayList() {
        return mAdapter.getSelectedDayList();
    }

    protected DatePickerController getController() {
        return mController;
    }

    public void setIsEnableMultiDaySelected(boolean isEnable) {
        if (mAdapter != null) {
            mAdapter.setEnableMultiDaySelect(isEnable);
        }
    }

    public void removeAllSelectedDays() {
        if (mAdapter != null) {
            mAdapter.removeAllSelectedDays();
        }
    }

    protected TypedArray getTypedArray() {
        return typedArray;
    }

    public void setEvents(ArrayList<Event> events) {
        if (mAdapter != null) {
            mAdapter.setEvents(events);
        }
    }
}
