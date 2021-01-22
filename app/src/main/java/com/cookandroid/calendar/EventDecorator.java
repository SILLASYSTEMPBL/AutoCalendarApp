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

public class EventDecorator implements DayViewDecorator {
    Context context;
    private CalendarDay date;
    myDBHelper database;
    SQLiteDatabase sqlDB = null ;
    boolean any = false;
    int schedule=0;
    ArrayList<Integer> Color = new ArrayList<Integer>();

    public EventDecorator(Context context){
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        database = new myDBHelper(context);
        sqlDB = database.getWritableDatabase();
        any = false;
        schedule = 0;
        String MonthStr;
        if (day.getMonth()<9) MonthStr = '0'+String.valueOf(day.getMonth()+1);
        else MonthStr = String.valueOf(day.getMonth()+1);

        String DayStr = "";
        if (day.getDay()<10) DayStr = '0'+String.valueOf(day.getDay());
        else DayStr = String.valueOf(day.getDay());

        String sql = "select startDate,color from scheduleTable where startDate = "+day.getYear()+MonthStr+DayStr+";";
        System.out.println(sql);
        Cursor result = sqlDB.rawQuery(sql,null);

        while(result.moveToNext()){
            int startdate = result.getInt(0);
            int color= result.getInt(1);
            Color.add(color);
            schedule++;
            any=true;
        }
        result.close();
        return any;
    }

    @Override
    public void decorate(DayViewFacade view){

        //view.addSpan(AddTextToDates("ABC"));
        //view.addSpan(new AddTextToDates("안녕하세요감사해요잘있어요"));
//        view.addSpan(new AddTextToDates(0xFFFF0000,0));
//        view.addSpan(new AddTextToDates(0xff00ff00,1));
//        view.addSpan(new AddTextToDates(0xff0000ff,2));
        int colorData=0xFF000000;
        for(int i=0;i<=schedule;i++){
            colorData=0xFF000000;
//            if(Color.get(i)==1)colorData=0xFFFF0000;

            view.addSpan(new AddTextToDates(colorData,i));
        }
    }

    public void setDate(Date date){
        this.date = CalendarDay.from(date);
    }

}
