package com.cookandroid.calendar;

import android.text.style.LineHeightSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;

public class ScheduleDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        return weekday == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new AddTextToDates(0xFFFF0000,0));

    }
}
