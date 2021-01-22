package com.cookandroid.calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

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
    int schedule=1;
    ArrayList<Integer> Color = new ArrayList<Integer>();

    public EventDecorator(Context context){
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day){
        database = new myDBHelper(context);
        sqlDB = database.getWritableDatabase();
        String sql = "select startDate,color from scheduleTable where startDate = "+day.getYear()*10000+day.getMonth()*100+day.getDay()+";";
        Cursor result = sqlDB.rawQuery(sql, null);

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
        for(int i=0;i<schedule;i++){
         //   if(Color.get(schedule)==1)colorData=0xFFFF0000;

            view.addSpan(new AddTextToDates(colorData,schedule));
        }
    }

    public void setDate(Date date){
        this.date = CalendarDay.from(date);
    }

}
