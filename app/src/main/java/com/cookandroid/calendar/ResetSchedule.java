package com.cookandroid.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class ResetSchedule extends AppCompatActivity {
    String Year,Month,Day,Hour,Min;
    myDBHelper database;
    SQLiteDatabase sqlDB ;
    //    SharedPreferences setColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulemain);
        //      setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        EditText setTitle = (EditText)findViewById(R.id.setTitle);
        Spinner setAlarm = (Spinner)findViewById(R.id.setAlarm);
        Spinner setAlarmTime = (Spinner)findViewById(R.id.setAlarmTime);
        EditText Memo = (EditText)findViewById(R.id.Memo);
        final Button setButton = (Button)findViewById(R.id.setButton);
        final Button colorButton = (Button) findViewById(R.id.color);
        final Button startDButton = (Button) findViewById(R.id.startDateButton);
        final Button endDButton = (Button) findViewById(R.id.endDateButton);
        final Switch allDaySwitch = (Switch) findViewById(R.id.allDaySwitch);
        final LinearLayout startDayLayout = (LinearLayout) findViewById(R.id.start_Date_Layout);
        final LinearLayout endDayLayout = (LinearLayout) findViewById(R.id.end_Date_Layout);
        final myDBHelper database = new myDBHelper(this);
        sqlDB = database.getWritableDatabase();
        final SharedPreferences setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        SharedPreferences.Editor editor = setColor.edit();
        final SharedPreferences setStartDate = getSharedPreferences("startTimer",MODE_PRIVATE);
        final SharedPreferences.Editor editor1 = setStartDate.edit();
        final SharedPreferences setEndDate = getSharedPreferences("endTimer",MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = setEndDate.edit();
        editor.putInt("scheduleColor", Color.parseColor("#000000"));
        editor.apply();

        Intent intent = getIntent();
        final String righttitle = intent.getExtras().getString("Title2");

        Cursor c = sqlDB.rawQuery("SELECT * FROM scheduleTable WHERE title ='"+righttitle+"'", null);

        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    int startyymmdd = c.getInt(0);
                    int starthhmm = c.getInt(5);
                    int endyymmmdd = c.getInt(1);
                    int endhhmm = c.getInt(6);

                    setTitle.setText(righttitle);
                    setAlarm.setSelection(c.getInt(3));
                    setAlarmTime.setSelection(c.getInt(8));
                    colorButton.setBackgroundColor(c.getInt(7));
                    Memo.setText(c.getString(4));
                    startDButton.setText(startyymmdd/10000+"/"+startyymmdd%10000/100+"/"
                            +startyymmdd%100+" "+starthhmm/100+":"+starthhmm%100);
                    endDButton.setText(endyymmmdd/10000+"/"+endyymmmdd%10000/100+"/"
                            +endyymmmdd%100+" "+endhhmm/100+":"+endhhmm%100);

                } while (c.moveToNext());
            }


        }

        sqlDB.close();





        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorDialog colorDlg = new colorDialog(ResetSchedule.this);
                colorDlg.callFunction(colorButton);
            }
        });
        startDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog stDlg = new dateDialog(ResetSchedule.this);
                stDlg.callFunction(startDButton,setStartDate.getString("Year",""),setStartDate.getString("Month",""),
                        setStartDate.getString("Day",""),setStartDate.getString("Hour",""),setStartDate.getString("Min",""),0,endDButton);
            }
        });

        endDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog stDlg = new dateDialog(ResetSchedule.this);
                stDlg.callFunction(endDButton,setEndDate.getString("Year",""),setEndDate.getString("Month",""),
                        setEndDate.getString("Day",""),setEndDate.getString("Hour",""),setEndDate.getString("Min",""),1,startDButton);
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
                int color= setColor.getInt("scheduleColor",0xFF000000);
                int settime=setAlarmTime.getSelectedItemPosition();

                sqlDB = database.getWritableDatabase();
                System.out.println("이진우"+startdate);
                sqlDB.execSQL("UPDATE scheduleTable SET startdate ="+startdate+", enddate ="+enddate
                        +" , title='"+title+"' , alarm ="+alarm+" , memo ='"+memo+"' , starttime ="+starttime
                        +" , endtime ="+endtime+" , color="+color+" , settime ="+settime+" WHERE title='"+righttitle+"'" );

                sqlDB.close();
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(setIntent);
            }
        });
    }
}