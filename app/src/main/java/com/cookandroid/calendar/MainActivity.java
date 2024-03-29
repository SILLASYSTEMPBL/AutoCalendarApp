package com.cookandroid.calendar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements  OnDateSelectedListener{

    SharedPreferences msgData;
    SharedPreferences.Editor msgEditor;

    SharedPreferences setColor;
    ImageView test;
    private Activity activity;
    private View view;
    public static ListViewAdapter listviewadapter;
    public static ListView listview;

    TCP_Client tc;
    String msgString="";
    myDBHelper database;
    SQLiteDatabase sqlDB = null ;
    private MaterialCalendarView materialCalendarView;
    TextView textView ;
    OneDayDecorator oneDayDecorator;
    String url = "tmp_"+String.valueOf(System.currentTimeMillis())+".jpg";
    Uri mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

    




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences setting1 = getSharedPreferences("setting",MODE_PRIVATE);
        msgData = getSharedPreferences("DATAMSG",MODE_PRIVATE);
        msgEditor = msgData.edit();
        setColor = getSharedPreferences("backgroundColor",MODE_PRIVATE);
        int dayofweek ;
        dayofweek = setting1.getInt("startday",1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSelfPermission();

        activity = this;

        //Adapter 클래스 생성

        listviewadapter = new ListViewAdapter(activity);


        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        final Button button = (Button) findViewById(R.id.button);
        final Button setting = (Button) findViewById(R.id.SettingButton);

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
                            TakeFromAlbum();
                        } else if (item.getItemId() == R.id.action_menu2){
                            Toast.makeText(MainActivity.this,"텍스트 확인",Toast.LENGTH_SHORT).show();
                            msgEditor.putString("data","!!");
                            msgEditor.apply();
                            Intent schedule_Intent = new Intent(getApplicationContext(),ScheduleActivity.class);
                            startActivity(schedule_Intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        findViewById(R.id.SettingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setIntent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(setIntent);
            }
        });


        materialCalendarView.state().edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .setFirstDayOfWeek(dayofweek).commit();

        materialCalendarView.setPadding(0,0,0,0);

        //materialCalendarView.setTileHeight(160);
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.setTopbarVisible(true);
        materialCalendarView.setDynamicHeightEnabled(false);
        Intent secondIntent = getIntent();

        materialCalendarView.setBackgroundColor(setColor.getInt("backgroundColor",Color.parseColor("#ffffff")));
        oneDayDecorator = new OneDayDecorator();

        int colorcount = 8;
        int[] colorIndex = {0xffffffff,0xffadadad,0xff03fcfc,0xffffff00,0xff00ff00,0xffff00ff,0xffff8800,0xff8078ff};
        int lineMax = 6;
        EventDecorator[][] decorators = new EventDecorator[colorcount][lineMax];
        for (int K=0;K < colorcount;K++) {
            for (int i = 0; i < lineMax; i++)
                decorators[K][i] = new EventDecorator(colorIndex[K], i);
        }
        database = new myDBHelper(activity);
        sqlDB = database.getReadableDatabase();
        String sql = "select startDate,color from scheduleTable;";
        Cursor result = sqlDB.rawQuery(sql,null);
        Calendar calendar = Calendar.getInstance();
        while(result.moveToNext()){
            int date = result.getInt(0);
            int color = result.getInt(1);

            int year = date/10000;
            int month = (date/100)%100;
            int days = date%100;
            calendar.set(year,month-1,days);
            CalendarDay day = CalendarDay.from(calendar);
            for (int K=0;K<colorcount;K++) {
                if (color == colorIndex[K]) {
                    for (int i=0;i<lineMax;i++) {
                        boolean checker=true;
                        for (int J=0;J<colorcount;J++) {
                            if (decorators[J][i].haveDays(day) == true)
                                checker = false;
                        }
                        if (checker == true) {
                            decorators[K][i].addDate(day);
                            System.out.println("TagLog : Draw Line : "+i+" Color : "+colorIndex[K]+" day : "+day);
                            break;
                        }
                    }
                }
            }
        }

        result.close();

        //##################다가올 일정 table#############################//

        //View 설정
        TextView title1 = (TextView)findViewById(R.id.tableTitle1);
        TextView title2 = (TextView)findViewById(R.id.tableTitle2);
        TextView Date1 = (TextView)findViewById(R.id.tableDate1);
        TextView Date2 = (TextView)findViewById(R.id.tableDate2);

        //현재 날짜 설정
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String weekDay = weekdayFormat.format(currentTime);
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);



        database = new myDBHelper(activity);
        sqlDB = database.getReadableDatabase();
        Cursor c = sqlDB.rawQuery("select startDate,title from scheduleTable order by startDate asc",null);
        int i=0;
        while(c.moveToNext()){
            int date = c.getInt(0);
            String title = c.getString(1);


            int today =Integer.parseInt(year+month+day);
            if(date>today){
                if(i==0){
                    title1.setText(title);
                    Date1.setText(String.valueOf(date%10000/100)+"월 "+String.valueOf(date%100) +"일");

                }
                else if(i==1){
                    title2.setText(title);
                    Date2.setText(String.valueOf(date%10000/100)+"월 "+String.valueOf(date%100) +"일");
                }
                else{
                    break;
                }
                i++;
            }

        }

        c.close();


        //##################다가올 일정 table#############################//


        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new WeekdayDecorator(),
                oneDayDecorator
        );
        for (int K=0;K<colorcount;K++) {
            for (i = 0; i < lineMax; i++)
                materialCalendarView.addDecorator(decorators[K][i]);
        }
        materialCalendarView.setSelectedDate(CalendarDay.today());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i=0;i<length;i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TagLog : Permission ",permissions[i]);
                }
            }
        }
    }

    private void checkSelfPermission() {
        String temp = "";
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }

        if (TextUtils.isEmpty(temp) == false) {
            ActivityCompat.requestPermissions(this,temp.trim().split(" "),1);
        } else {
            Toast.makeText(this,"권한이 모두 허용상태입니다",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        int startDate = date.getYear()*10000 + (date.getMonth()+1)*100 + date.getDay();
        // 뷰 호출
        view = activity.getLayoutInflater().inflate(R.layout.listview, null);
        // 해당 뷰에 리스트뷰 호출
        listview = (ListView)view.findViewById(R.id.listView);
        // 리스트뷰에 어뎁터 설정
        listview.setAdapter(listviewadapter);

        listviewadapter.Clear();
        listviewadapter.notifyDataSetChanged();

        database = new myDBHelper(activity);
       sqlDB = database.getReadableDatabase();

        Cursor c = sqlDB.rawQuery("SELECT title,color FROM scheduleTable WHERE startdate ='"+startDate+"'", null);

        if (c != null) {


                if (c.moveToFirst()) {
                    do {

                        //테이블에서 두개의 컬럼값을 가져와서
                        String title = c.getString(0);
                        int color = c.getInt(1);
//                        Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
                       listviewadapter.setTitle(title,color);

                    } while (c.moveToNext());
                }


        }

        sqlDB.close();

        //대화상자 생성 코드
        AlertDialog.Builder listViewDialog = new AlertDialog.Builder(activity);

        listViewDialog.setView(view);

        listViewDialog.setPositiveButton("확인", null);
        listViewDialog.setTitle((date.getMonth()+1)+"월 "+date.getDay()+"일 ");
        AlertDialog msgDlg = listViewDialog.create();
        msgDlg.show();






    }

    public void TakeFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,mImageCaptureUri);
        startActivityForResult(intent,2);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != RESULT_OK) return;
        switch(requestCode) {
            case 1:
            {
                Log.i("TagLog : CheckImage",String.format("%d",requestCode));
                mImageCaptureUri = data.getData();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(mImageCaptureUri,proj,null,null,null);
                c.moveToFirst();
                int c_index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String imagePath = c.getString(c_index);
                boolean ch = true;
                tc = new TCP_Client(this,imagePath);
                Thread thread = new Thread(tc);
                //new TCP_task(imagePath).execute(this);
                thread.setDaemon(true);
                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {

                }
                Intent shedule_Image_Intent = new Intent(getApplicationContext(),ScheduleActivity.class);
                startActivity(shedule_Image_Intent);
          //      Toast.makeText(MainActivity.this,data.getDataString(),Toast.LENGTH_SHORT).show();
                break;
            }

            case 2:
            {
                Toast.makeText(MainActivity.this,"TEXT!",Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
