package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.motion.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class dateDialog extends AppCompatActivity {
    private Context context;
    SharedPreferences s_timer;
    SharedPreferences.Editor editor;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner daySpinner;

    public dateDialog(Context context) {
        this.context = context;

        s_timer = context.getSharedPreferences("startTimer",MODE_PRIVATE);
        editor = s_timer.edit();
    }

    protected void callFunction(final Button button,String Y,String M,String D,String H,String Minute,int Type) {
        final Dialog dlg = new Dialog(context);
        String Year = Y;
        String Month = M;
        String Day = D;
        String Hour = H;
        String Min = Minute;

        if(Type==0) s_timer = context.getSharedPreferences("startTimer",MODE_PRIVATE);
        else s_timer = context.getSharedPreferences("endTimer",MODE_PRIVATE);
        editor = s_timer.edit();
        dlg.setContentView(R.layout.schedule_date);
        dlg.show();

        final Button cancel = (Button) dlg.findViewById(R.id.dateCancel);
        final Button accept = (Button) dlg.findViewById(R.id.dateAccept);

        yearSpinner = (Spinner) dlg.findViewById(R.id.YearSpinner);
        monthSpinner = (Spinner) dlg.findViewById(R.id.MonthSpinner);
        //Log.i("WTF", "data : "+Integer.parseInt(Month));
        monthSpinner.setSelection(Integer.parseInt(Month)-1);
        //monthSpinner.setSelection(0);
        setDaySpinnerValue(dlg, Integer.parseInt(Year),Integer.parseInt(Day));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
    }

    public void setDaySpinnerValue(Dialog dlg, int Year,int Day) {
        int Position = monthSpinner.getSelectedItemPosition()+1;
        boolean leap = false;
        if (Year%4==0&&(Year%100!=0||Year%400==0)) leap = true;
        int dayNum=0;
        switch(Position) {
            case 2:
                dayNum=28;
                if (leap) dayNum=29;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dayNum=30;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 10:
            case 12:
                dayNum=31;
                break;
        }
        arrayList = new ArrayList<String>();
        for (int i=1;i<=dayNum;i++) {
            arrayList.add(String.valueOf(i));
        }
        arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,arrayList);
        daySpinner = (Spinner)dlg.findViewById(R.id.DaySpinner);
        daySpinner.setAdapter(arrayAdapter);
        if(Day>dayNum) daySpinner.setSelection(0);
        else daySpinner.setSelection(Day-1);
    }
}
