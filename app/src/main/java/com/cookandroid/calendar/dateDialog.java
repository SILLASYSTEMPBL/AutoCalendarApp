package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.motion.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

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

    ArrayList<String> yearList;
    ArrayAdapter<String> yearAdapter;

    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner daySpinner;

    TimePicker timeP;

    public dateDialog(Context context) {
        this.context = context;

        s_timer = context.getSharedPreferences("startTimer",MODE_PRIVATE);
        editor = s_timer.edit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void callFunction(final Button button, String Y, String M, String D, String H, String Minute, int Type) {
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
        setYearSpinnerValue(dlg, Integer.parseInt(Year));

        timeP = (TimePicker) dlg.findViewById(R.id.TimePicker);
        timeP.setHour(Integer.parseInt(Hour));
        timeP.setMinute(Integer.parseInt(Min));

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setDaySpinnerValue(dlg,Integer.parseInt(yearSpinner.getSelectedItem().toString()),Integer.parseInt(daySpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setDaySpinnerValue(dlg,Integer.parseInt(yearSpinner.getSelectedItem().toString()),Integer.parseInt(daySpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("Year",yearSpinner.getSelectedItem().toString());
                editor.putString("Month",monthSpinner.getSelectedItem().toString());
                editor.putString("Day",daySpinner.getSelectedItem().toString());
                if (timeP.getHour()<10) editor.putString("Hour",'0'+String.valueOf(timeP.getHour()));
                else editor.putString("Hour",String.valueOf(timeP.getHour()));
                if (timeP.getMinute()<10) editor.putString("Min",'0'+String.valueOf(timeP.getMinute()));
                else editor.putString("Min",String.valueOf(timeP.getMinute()));
                editor.apply();
                button.setText(s_timer.getString("Year","")+"/"+s_timer.getString("Month",""+"")+"/"
                        +s_timer.getString("Day","")+" "+s_timer.getString("Hour","")+":"+s_timer.getString("Min",""));
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

    public void setYearSpinnerValue(Dialog dlg,int Year) {
        yearList = new ArrayList<String>();
        for (int i=0;i<10;i++) {
            yearList.add(String.valueOf(Year+i));
        }
        yearAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,yearList);
        yearSpinner = (Spinner)dlg.findViewById(R.id.YearSpinner);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(0);
    }
}
