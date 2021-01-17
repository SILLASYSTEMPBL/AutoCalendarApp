package com.cookandroid.calendar;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

class WeekdayDecorator implements DayViewDecorator{
    private final Calendar calendar = Calendar.getInstance();
    public WeekdayDecorator(){

    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        day.copyTo(calendar);
        boolean val=false;
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (1<weekDay&&weekDay<7) val=true;
        return val;
    }

    @Override
    public void decorate(DayViewFacade view){
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
    }

}