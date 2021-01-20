package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class colorDialog extends AppCompatActivity {

    SharedPreferences setColor = getSharedPreferences("settingColor",MODE_PRIVATE);
    SharedPreferences.Editor editor = setColor.edit();
    private Context context;

    public colorDialog(Context context) {
        this.context = context;
    }

    public void callFunction() {

        final Dialog dlg = new Dialog(context);
        dlg.setContentView(R.layout.schedule_color);
        dlg.show();



        final Button cancel = (Button) dlg.findViewById(R.id.cancel);
        final Button black = (Button) dlg.findViewById(R.id.button_black);
        final Button red = (Button) dlg.findViewById(R.id.button_red);
        final Button blue = (Button) dlg.findViewById(R.id.button_blue);
        final Button yellow = (Button) dlg.findViewById(R.id.button_yellow);
        final Button pupple = (Button) dlg.findViewById(R.id.button_pupple);
        final Button cyan = (Button) dlg.findViewById(R.id.button_cyan);
        final Button orange = (Button) dlg.findViewById(R.id.button_orange);
        final Button green = (Button) dlg.findViewById(R.id.button_green);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                editor.putInt("scheduleColor", Color.parseColor("#000000"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#ff0000"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#0000ff"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#ffff00"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        pupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#ff00ff"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#8078ff"));
//                editor.apply();
                dlg.dismiss();
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#ff8800" ));
//                editor.apply();
                dlg.dismiss();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putInt("scheduleColor", Color.parseColor("#00ff00"));
//                editor.apply();
                dlg.dismiss();
            }
        });
    }
}
