package com.cookandroid.calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class selectDay extends AppCompatActivity {

    private Activity activity;
    myDBHelper database;
    SQLiteDatabase sqlDB = null ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectday);

        database = new myDBHelper(activity);
        sqlDB = database.getReadableDatabase();

        TextView title = (TextView)findViewById(R.id.titleSelectDay);
        TextView startdate = (TextView)findViewById(R.id.select_stardate);
        TextView enddate = (TextView)findViewById(R.id.select_enddate);

        TextView alarm = (TextView)findViewById(R.id.selectedAlarm);
        TextView settime = (TextView)findViewById(R.id.selectedAlarmTime);
        TextView memo = (TextView)findViewById(R.id.memoSelect);

        Intent intent = getIntent();
        String righttitle = intent.getExtras().getString("title")
        title.setText(righttitle);


        Cursor c = sqlDB.rawQuery("SELECT * FROM scheduleTable WHERE title ='"+righttitle+"'", null);

        if (c != null) {


            if (c.moveToFirst()) {
                do {

                    //테이블에서 두개의 컬럼값을 가져와서
//                    String title = c.getString(0);
                  int color = c.getInt(1);
//                        Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();


                } while (c.moveToNext());
            }


        }

//        sqlDB.close();
//        startdate.setText();
//        enddate.setText();
//        alarm.setText();
//        settime.setText();
//        memo.setText();
    }
}
