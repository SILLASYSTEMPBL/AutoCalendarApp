package com.cookandroid.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context){
        super(context,"ScheduleDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE scheduleTable (startdate int,enddate int,title char(30) PRIMARY KEY,alarm char(1),memo char(200),starttime int,endtime int,color char(1),settime char(1))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS scheduleTable");
        onCreate(db);
    }
}
