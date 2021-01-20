package com.cookandroid.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.Switch;

public class ScheduleActivity extends AppCompatActivity {

//    SharedPreferences setColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
  //      setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulemain);



        final Button colorButton = (Button) findViewById(R.id.color);
        final Switch allDaySwitch = (Switch) findViewById(R.id.allDaySwitch);

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorDialog colorDlg = new colorDialog(ScheduleActivity.this);
                colorDlg.callFunction(colorButton);
                }
        });
    }
}