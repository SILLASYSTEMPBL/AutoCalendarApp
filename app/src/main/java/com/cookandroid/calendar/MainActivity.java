package com.cookandroid.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements  OnDateSelectedListener{

    private MaterialCalendarView materialCalendarView;
    TextView textView ;
    OneDayDecorator oneDayDecorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        final Button button = (Button) findViewById(R.id.button);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_menu1){
                            Toast.makeText(MainActivity.this,"이미지 확인",Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.action_menu2){
                            Toast.makeText(MainActivity.this,"텍스트 확인",Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        materialCalendarView.state().edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS);

        materialCalendarView.setPadding(0,0,0,0);

        //materialCalendarView.setTileHeight(160);
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setTopbarVisible(true);
        materialCalendarView.setDynamicHeightEnabled(false);
        oneDayDecorator = new OneDayDecorator();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new WeekdayDecorator(),
                new EventDecorator(),
                oneDayDecorator
        );
        materialCalendarView.setSelectedDate(CalendarDay.today());
        textView = (TextView)findViewById(R.id.yymmdd);
        textView.setText("다가올 일정");

    }

    public class myDBHelper extends SQLiteOpenHelper{
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
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        textView = (TextView)findViewById(R.id.yymmdd);
        textView.setText(date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDay()+"일");
    }
}
