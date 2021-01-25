package com.cookandroid.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    SharedPreferences setColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final myDBHelper database = new myDBHelper(this);
        final SQLiteDatabase sqlDB = database.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final Button button3 = (Button)findViewById(R.id.button3);
        final Button emergencyTrigger = (Button)findViewById(R.id.button5);

        //final TextView spinnerTxt = findViewById(R.id.textView4);
        setColor = getSharedPreferences("backgroundColor",MODE_PRIVATE);
        button3.setBackgroundColor(setColor.getInt("backgroundColor",Color.LTGRAY));

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorDialog colorDlg = new colorDialog(SettingActivity.this);
                colorDlg.callFunction_2(button3);
            }
        });

        emergencyTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences setting = getSharedPreferences("setting",MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                SharedPreferences.Editor editor1 = setColor.edit();
                spinner.setSelection(0);
                editor1.putInt("backgroundColor", android.R.color.white);
                editor.putInt("startday",1);
                button3.setBackgroundColor(setColor.getInt("backgroundColor",Color.LTGRAY));
                editor.apply();
                editor1.apply();
                database.onUpgrade(sqlDB,1,2);
                sqlDB.close();

                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
//                setIntent.putExtra("background",setColor.getInt("backgroundColor", Color.parseColor("#ffffff")));
                startActivity(setIntent);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable color = (ColorDrawable) button3.getBackground();
                SharedPreferences setting = getSharedPreferences("setting",MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                SharedPreferences.Editor editor1 = setColor.edit();
                String startDay = spinner.getSelectedItem().toString();
                if(startDay.equals("일요일")){
                    editor.putInt("startday",1);
                }
                else if(startDay.equals("월요일")) {
                    editor.putInt("startday", 2);
                }
                editor.apply();

                editor1.putInt("backgroundColor", color.getColor());
                editor1.apply();
                sqlDB.close();
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
//                setIntent.putExtra("background",setColor.getInt("backgroundColor", Color.parseColor("#ffffff")));
                startActivity(setIntent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB.close();
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(setIntent);
            }
        });
    }
}
