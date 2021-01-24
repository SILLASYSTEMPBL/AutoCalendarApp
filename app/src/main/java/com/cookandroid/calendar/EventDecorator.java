package com.cookandroid.calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private int lineenum;
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(int color , int linenum){
        this.color = color;
        this.lineenum = linenum;
        dates = new HashSet<>();
    }

    public boolean addDate(CalendarDay day) {
        return dates.add(day);
    }
    @Override
    public boolean shouldDecorate(CalendarDay day){
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view){
        view.addSpan(new AddTextToDates(color,lineenum));
    }

    public boolean haveDays(CalendarDay day) {
        return dates.contains(day);
    }
}
