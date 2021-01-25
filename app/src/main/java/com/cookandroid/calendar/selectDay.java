package com.cookandroid.calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class selectDay extends AppCompatActivity  {

    private Activity activity;
    myDBHelper database;
    SQLiteDatabase sqlDB ;
    String[]alarmSet = {"없음","무음","진동","소리","진동+소리"};
    String[]alarmTime ={"정각","5분전","10분전","15분전","30분전","1시간전"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectday);

        activity = this;

        database = new myDBHelper(activity);
        sqlDB = database.getReadableDatabase();

        TextView title = (TextView)findViewById(R.id.titleSelectDay);
        TextView startdate = (TextView)findViewById(R.id.select_stardate);
        TextView enddate = (TextView)findViewById(R.id.select_enddate);
        Button colorbutton = (Button)findViewById(R.id.colorSelectDay) ;
        TextView alarm = (TextView)findViewById(R.id.selectedAlarm);
        TextView settime = (TextView)findViewById(R.id.selectedAlarmTime);
        TextView memo = (TextView)findViewById(R.id.memoSelect);
        Button setAgain = (Button)findViewById(R.id.setagain);
        Button Home = (Button)findViewById(R.id.home);


        Intent intent = getIntent();
        final String righttitle = intent.getExtras().getString("title");
        title.setText(righttitle);



            Cursor c = sqlDB.rawQuery("SELECT * FROM scheduleTable WHERE title ='"+righttitle+"' AND startdate", null);

            if (c != null) {

            if (c.moveToFirst()) {
                do {
                    int startyymmdd = c.getInt(0);
                    int starthhmm = c.getInt(5);
                    int endyymmmdd = c.getInt(1);
                    int endhhmm = c.getInt(6);

                    startdate.setText(String.valueOf(startyymmdd/10000)+"년 "+String.valueOf(startyymmdd%10000/100)+"월 "+String.valueOf(startyymmdd%100)+"일 "+String.valueOf(starthhmm/100)+"시 "+String.valueOf(starthhmm%100)+"분");
                    enddate.setText(String.valueOf(endyymmmdd/10000)+"년 "+String.valueOf(endyymmmdd%10000/100)+"월 "+String.valueOf(endyymmmdd%100)+"일 "+String.valueOf(endhhmm/100)+"시 "+String.valueOf(endhhmm%100)+"분");
                    alarm.setText(alarmSet[c.getInt(3)]);
                    memo.setText(c.getString(4));
                    settime.setText(alarmTime[c.getInt(8)]);
                    colorbutton.setBackgroundColor(c.getInt(7));

                } while (c.moveToNext());
            }


        }

        sqlDB.close();


            setAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity.getApplicationContext(),ResetSchedule.class);
                    intent.putExtra("Title2",righttitle);
                    activity.startActivity(intent);
                }
            });
            Home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity.getApplicationContext(),MainActivity.class);

                    activity.startActivity(intent);
                }
            });
    }
}
