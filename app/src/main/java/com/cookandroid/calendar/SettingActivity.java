package com.cookandroid.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Spinner spinner = findViewById(R.id.spinner);
        //final TextView spinnerTxt = findViewById(R.id.textView4);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            SharedPreferences setting = getSharedPreferences("setting",MODE_PRIVATE);
            SharedPreferences.Editor editor = setting.edit();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String startDay;

                startDay = parent.getItemAtPosition(position).toString();

                if(startDay.equals("일요일")){
                    editor.putInt("startday",1);
                }
                else if(startDay.equals("월요일")) {
                    editor.putInt("startday", 2);
                }
                editor.commit();

            }
     ;

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(setIntent);
            }
        });
    }
}
