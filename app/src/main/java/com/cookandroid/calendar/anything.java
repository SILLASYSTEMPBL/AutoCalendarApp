//package com.cookandroid.calendar;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//
//
//public class anything extends AppCompatActivity {
//
//    myDBHelper database;
//    SQLiteDatabase sqlDB ;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.selectday);
//        final myDBHelper database = new myDBHelper(this);
//        sqlDB = database.getReadableDatabase();
//        database.onUpgrade(sqlDB,1,2);
//        sqlDB.close();
//
//    }
//    // Data 읽기(꺼내오기)
//    public void selectData(int index){
//        String sql = "select * from scheduleTable where startDate = "+index+";";
//        Cursor result = sqlDB.rawQuery(sql, null);
//
//        // result(Cursor 객체)가 비어 있으면 false 리턴
//        if(result.moveToFirst()){
//            int startdate = result.getInt(0);
//            int enddate = result.getInt(1);
//            String title = result.getString(2);
//            int alarm = result.getInt(3);
//            String memo  = result.getString(4);
//            int starttime = result.getInt(5);
//            int endtime = result.getInt(6);;
//            int color= result.getInt(7);
//            int settime=result.getInt(8);
//
////            Toast.makeText(this, "index= "+id+" voca="+voca, 0).show();
//        }
//        result.close();
//    }
//}
