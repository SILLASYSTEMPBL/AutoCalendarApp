package com.cookandroid.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {
    String Year,Month,Day,Hour,Min;
    myDBHelper database;
    SQLiteDatabase sqlDB ;
//    SharedPreferences setColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
  //      setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        final myDBHelper database = new myDBHelper(this);
        SharedPreferences setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        SharedPreferences.Editor editor = setColor.edit();
        final SharedPreferences setStartDate = getSharedPreferences("startTimer",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = setStartDate.edit();
        final SharedPreferences setEndDate = getSharedPreferences("endTimer",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = setEndDate.edit();
        editor.putInt("scheduleColor", Color.parseColor("#000000"));
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulemain);

        SimpleDateFormat dateFormat;
        long mNow = System.currentTimeMillis();
        Min = "00";
        Date date = new Date(mNow);
        dateFormat = new SimpleDateFormat("yyyy");
        Year = dateFormat.format(date);
        editor1.putString("Year",Year);
        dateFormat = new SimpleDateFormat("MM");
        Month = dateFormat.format(date);
        editor1.putString("Month",Month);
        dateFormat = new SimpleDateFormat("dd");
        Day = dateFormat.format(date);
        editor1.putString("Day",Day);
        dateFormat = new SimpleDateFormat("HH");
        Hour = dateFormat.format(date);
        editor1.putString("Hour",Hour);
        editor1.putString("Min",Min);

        mNow += 3600000;
        date = new Date(mNow);
        dateFormat = new SimpleDateFormat("yyyy");
        Year = dateFormat.format(date);
        editor2.putString("Year",Year);
        dateFormat = new SimpleDateFormat("MM");
        Month = dateFormat.format(date);
        editor2.putString("Month",Month);
        dateFormat = new SimpleDateFormat("dd");
        Day = dateFormat.format(date);
        editor2.putString("Day",Day);
        dateFormat = new SimpleDateFormat("HH");
        Hour = dateFormat.format(date);
        editor2.putString("Hour",Hour);
        editor2.putString("Min",Min);

        editor1.apply();
        editor2.apply();
        final Button setButton = (Button)findViewById(R.id.setButton);
        final Button colorButton = (Button) findViewById(R.id.color);
        final Button startDButton = (Button) findViewById(R.id.startDateButton);
        final Button endDButton = (Button) findViewById(R.id.endDateButton);
        final Switch allDaySwitch = (Switch) findViewById(R.id.allDaySwitch);
        final LinearLayout startDayLayout = (LinearLayout) findViewById(R.id.start_Date_Layout);
        final LinearLayout endDayLayout = (LinearLayout) findViewById(R.id.end_Date_Layout);
        colorButton.setBackgroundColor(Color.BLACK);

        startDButton.setText(setStartDate.getString("Year","")+"/"+setStartDate.getString("Month",""+"")+"/"
                +setStartDate.getString("Day","")+" "+setStartDate.getString("Hour","")+":"+setStartDate.getString("Min",""));

        endDButton.setText(setEndDate.getString("Year","")+"/"+setEndDate.getString("Month",""+"")+"/"
                +setEndDate.getString("Day","")+" "+setEndDate.getString("Hour","")+":"+setEndDate.getString("Min",""));

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorDialog colorDlg = new colorDialog(ScheduleActivity.this);
                colorDlg.callFunction(colorButton);
                }
        });
        startDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog stDlg = new dateDialog(ScheduleActivity.this);
                stDlg.callFunction(startDButton,setStartDate.getString("Year",""),setStartDate.getString("Month",""),
                        setStartDate.getString("Day",""),setStartDate.getString("Hour",""),setStartDate.getString("Min",""),0);
            }
        });

        endDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog stDlg = new dateDialog(ScheduleActivity.this);
                stDlg.callFunction(endDButton,setEndDate.getString("Year",""),setEndDate.getString("Month",""),
                        setEndDate.getString("Day",""),setEndDate.getString("Hour",""),setEndDate.getString("Min",""),1);
            }
        });

        allDaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    startDayLayout.setVisibility(View.INVISIBLE);
                    //startDayLayout.setScaleY(0.01f);
                    endDayLayout.setVisibility(View.INVISIBLE);
                }
                else {
                    startDayLayout.setVisibility(View.VISIBLE);
                    endDayLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText setTitle = (EditText)findViewById(R.id.setTitle);
                Spinner setAlarm = (Spinner)findViewById(R.id.setAlarm);
                Spinner setAlarmTime = (Spinner)findViewById(R.id.setAlarmTime);
                EditText Memo = (EditText)findViewById(R.id.Memo);
                int startdate = Integer.parseInt(setStartDate.getString("Year","")+setStartDate.getString("Month","")+setStartDate.getString("Day",""));
                int enddate = Integer.parseInt(setEndDate.getString("Year","")+setEndDate.getString("Month","")+setEndDate.getString("Day",""));
                String title = setTitle.getText().toString();
                int alarm = setAlarm.getSelectedItemPosition();
                String memo  = Memo.getText().toString();
                int starttime = Integer.parseInt(setStartDate.getString("Hour","")+setStartDate.getString("Min",""));;
                int endtime = Integer.parseInt(setEndDate.getString("Hour","")+setEndDate.getString("Min",""));;
                int color= 1;
                int settime=setAlarmTime.getSelectedItemPosition();

                sqlDB = database.getWritableDatabase();
                database.onUpgrade(sqlDB,1,2);
                System.out.println(startdate);
                sqlDB.execSQL("INSERT INTO scheduleTable VALUES('"+startdate+"','"+enddate+"','"+title+"','"+alarm+"','"+memo+"','"+starttime+"','"+endtime+"','"+color+"','"+settime+"'); ");
                sqlDB.close();
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(setIntent);
            }
        });
    }
}