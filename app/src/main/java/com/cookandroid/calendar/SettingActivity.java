package com.cookandroid.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
                //spinnerTxt.setText((CharSequence) parent.getItemAtPosition(position));
=======
                    //spinnerTxt.setText((CharSequence) parent.getItemAtPosition(position));
>>>>>>> 429e366d7b6f056534862963f90acfd0e528c02a
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
