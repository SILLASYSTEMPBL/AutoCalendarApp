package com.cookandroid.calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        final SharedPreferences setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        final SharedPreferences msgData = getSharedPreferences("DATAMSG",MODE_PRIVATE);
        SharedPreferences.Editor msgEditor = msgData.edit();
        SharedPreferences.Editor editor = setColor.edit();
        final SharedPreferences setStartDate = getSharedPreferences("startTimer",MODE_PRIVATE);
        final SharedPreferences.Editor editor1 = setStartDate.edit();
        final SharedPreferences setEndDate = getSharedPreferences("endTimer",MODE_PRIVATE);
        final SharedPreferences.Editor editor2 = setEndDate.edit();
        editor.putInt("scheduleColor", Color.parseColor("#ffffff"));
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulemain);

        final Button setButton = (Button)findViewById(R.id.setButton);
        final Button cancelButton = (Button)findViewById(R.id.CancelButton);
        final Button colorButton = (Button) findViewById(R.id.color);
        final Button startDButton = (Button) findViewById(R.id.startDateButton);
        final Button endDButton = (Button) findViewById(R.id.endDateButton);
        final Switch allDaySwitch = (Switch) findViewById(R.id.allDaySwitch);
        final LinearLayout startDayLayout = (LinearLayout) findViewById(R.id.start_Date_Layout);
        final LinearLayout endDayLayout = (LinearLayout) findViewById(R.id.end_Date_Layout);
        EditText setTitle = (EditText)findViewById(R.id.setTitle);
        colorButton.setBackgroundColor(Color.WHITE);


        System.out.println("TagLog : "+msgData.getString("data","NULL"));
        if(msgData.getString("data","NULL").equals("!!")) {
            SimpleDateFormat dateFormat;
            long mNow = System.currentTimeMillis();
            Min = "00";
            Date date = new Date(mNow);
            dateFormat = new SimpleDateFormat("yyyy");
            Year = dateFormat.format(date);
            editor1.putString("Year", Year);
            dateFormat = new SimpleDateFormat("MM");
            Month = dateFormat.format(date);
            editor1.putString("Month", Month);
            dateFormat = new SimpleDateFormat("dd");
            Day = dateFormat.format(date);
            editor1.putString("Day", Day);
            dateFormat = new SimpleDateFormat("HH");
            Hour = dateFormat.format(date);
            editor1.putString("Hour", Hour);
            editor1.putString("Min", Min);

            mNow += 3600000;
            date = new Date(mNow);
            dateFormat = new SimpleDateFormat("yyyy");
            Year = dateFormat.format(date);
            editor2.putString("Year", Year);
            dateFormat = new SimpleDateFormat("MM");
            Month = dateFormat.format(date);
            editor2.putString("Month", Month);
            dateFormat = new SimpleDateFormat("dd");
            Day = dateFormat.format(date);
            editor2.putString("Day", Day);
            dateFormat = new SimpleDateFormat("HH");
            Hour = dateFormat.format(date);
            editor2.putString("Hour", Hour);
            editor2.putString("Min", Min);
            editor1.apply();
            editor2.apply();
        }
        else {
            String inputData = msgData.getString("data","!!");
            String[] arrayStr = inputData.split("!");
            if (!arrayStr[0].equals("")) {
                Log.i("TagLog : array0 : ",arrayStr[0]);
                setTitle.setText(arrayStr[0]);
            }
            if (!arrayStr[1].equals("")) {
                Log.i("TagLog : array1 : ",arrayStr[1]);
                String firstStr = arrayStr[1];
                Log.i("TagLog : first : ",firstStr);
                String[] startStr = arrayStr[1].split(" ");
                Log.i("TagLog : 1 - array0 : ",startStr[0]);
                Log.i("TagLog : 1 - array0 : ",startStr[1]);
                Log.i("TagLog : 1 - array0 : ",startStr[2]);
                Log.i("TagLog : 1 - array0 : ",startStr[3]);
                Log.i("TagLog : 1 - array0 : ",startStr[4]);
                editor1.putString("Year",startStr[0]);
                editor1.putString("Month",startStr[1]);
                editor1.putString("Day",startStr[2]);
                editor1.putString("Hour",startStr[3]);
                editor1.putString("Min",startStr[4]);
                editor1.apply();
            }
            if (!arrayStr[2].equals("")) {
                Log.i("TagLog : array2 : ",arrayStr[2]);
                String[] endStr = arrayStr[2].split(" ");
                editor2.putString("Year",endStr[0]);
                editor2.putString("Month",endStr[1]);
                editor2.putString("Day",endStr[2]);
                editor2.putString("Hour",endStr[3]);
                editor2.putString("Min",endStr[4]);
                editor2.apply();
            }
        }
        msgEditor.putString("data","");

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
                        setStartDate.getString("Day",""),setStartDate.getString("Hour",""),setStartDate.getString("Min",""),0,endDButton);
            }
        });

        endDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog stDlg = new dateDialog(ScheduleActivity.this);
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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sqlDB.close();
                Intent setIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(setIntent);
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.YEAR, Integer.parseInt(setStartDate.getString("Year","")));
                calendar.set(Calendar.MONTH, Integer.parseInt(setStartDate.getString("Month",""))-1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(setStartDate.getString("Day","")));
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(setStartDate.getString("Hour","")));
                calendar.set(Calendar.MINUTE, Integer.parseInt(setStartDate.getString("Min","")));
                calendar.set(Calendar.SECOND, 0);

                EditText setTitle = (EditText)findViewById(R.id.setTitle);
                Spinner setAlarm = (Spinner)findViewById(R.id.setAlarm);
                Spinner setAlarmTime = (Spinner)findViewById(R.id.setAlarmTime);
                if(setTitle.getText().toString().length()!=0) {
                    int alarmtimeset = setAlarmTime.getSelectedItemPosition();
                    switch (alarmtimeset) {
                        case 0:
                            break;
                        case 1:
                            calendar.add(Calendar.MINUTE, -5);
                            break;
                        case 2:
                            calendar.add(Calendar.MINUTE, -10);
                            break;
                        case 3:
                            calendar.add(Calendar.MINUTE, -15);
                            break;
                        case 4:
                            calendar.add(Calendar.MINUTE, -30);
                            break;
                        case 5:
                            calendar.add(Calendar.HOUR_OF_DAY, -1);
                            break;
                        case 6:
                            calendar.add(Calendar.DAY_OF_MONTH, -1);
                            break;
                    }
                    System.out.println("TagLog : " + calendar.getTime());

                    EditText Memo = (EditText) findViewById(R.id.Memo);
                    int startdate = Integer.parseInt(setStartDate.getString("Year", "") + setStartDate.getString("Month", "") + setStartDate.getString("Day", ""));
                    int enddate = Integer.parseInt(setEndDate.getString("Year", "") + setEndDate.getString("Month", "") + setEndDate.getString("Day", ""));
                    String title = setTitle.getText().toString();
                    int alarm = setAlarm.getSelectedItemPosition();
                    String memo = Memo.getText().toString();
                    int starttime = Integer.parseInt(setStartDate.getString("Hour", "") + setStartDate.getString("Min", ""));
                    ;
                    int endtime = Integer.parseInt(setEndDate.getString("Hour", "") + setEndDate.getString("Min", ""));
                    ;
                    int color = setColor.getInt("scheduleColor", 0xFF000000);
                    int settime = setAlarmTime.getSelectedItemPosition();

                    diaryNotification(calendar, alarm,starttime,endtime);

                    sqlDB = database.getWritableDatabase();

                    System.out.println(startdate);
                    sqlDB.execSQL("INSERT INTO scheduleTable VALUES('" + startdate + "','" + enddate + "','" + title + "','" + alarm + "','" + memo + "','" + starttime + "','" + endtime + "','" + color + "','" + settime + "'); ");
                    sqlDB.close();
                    Intent setIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(setIntent);
                }
                else Toast.makeText(ScheduleActivity.this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void diaryNotification(Calendar calendar,int alarm,int starttime,int endtime) {
        boolean notify = true;

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        int Code = (starttime%10000)*10000 + endtime;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Code, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }

    public void canthis() {

    }
}