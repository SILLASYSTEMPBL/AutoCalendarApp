package com.cookandroid.calendar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Date;

public class EventDecorator implements DayViewDecorator {
    private CalendarDay date;

    public EventDecorator(){
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        return day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view){
        //view.addSpan(AddTextToDates("ABC"));
        //view.addSpan(new AddTextToDates("안녕하세요감사해요잘있어요"));
        view.addSpan(new AddTextToDates(0xFFFF0000,0));
        view.addSpan(new AddTextToDates(0xff00ff00,1));
        view.addSpan(new AddTextToDates(0xff0000ff,2));
    }

    public void setDate(Date date){
        this.date = CalendarDay.from(date);
    }
}
