package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class colorDialog extends AppCompatActivity{

    private Context context;
    SharedPreferences setColor;
    SharedPreferences.Editor editor;

    private int data;

    public colorDialog(Context context) {
        this.context = context;

    }

    protected void callFunction(final Button button) {
        final Dialog dlg = new Dialog(context);
        setColor = context.getSharedPreferences("settingColor",MODE_PRIVATE);
        editor = setColor.edit();
        dlg.setContentView(R.layout.schedule_color);
        dlg.show();



        final Button cancel = (Button) dlg.findViewById(R.id.cancel);
        final Button white = (Button) dlg.findViewById(R.id.button_white);
        final Button gray= (Button) dlg.findViewById(R.id.button_gray);
        final Button sky = (Button) dlg.findViewById(R.id.button_sky);
        final Button yellow = (Button) dlg.findViewById(R.id.button_yellow);
        final Button pupple = (Button) dlg.findViewById(R.id.button_pupple);
        final Button cyan = (Button) dlg.findViewById(R.id.button_cyan);
        final Button orange = (Button) dlg.findViewById(R.id.button_orange);
        final Button green = (Button) dlg.findViewById(R.id.button_green);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("scheduleColor", Color.parseColor("#ffffff"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#adadad"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#03fcfc"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#ffff00"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        pupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#ff00ff"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#8078ff"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#ff8800" ));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("scheduleColor", Color.parseColor("#00ff00"));
                editor.apply();
                button.setBackgroundColor(setColor.getInt("scheduleColor",0));
                dlg.dismiss();
            }
        });
    }

    protected void callFunction_2(final Button button) {
        final Dialog dlg = new Dialog(context);
        dlg.setContentView(R.layout.schedule_color);
        dlg.show();


        final Button cancel = (Button) dlg.findViewById(R.id.cancel);
        final Button white = (Button) dlg.findViewById(R.id.button_white);
        final Button gray = (Button) dlg.findViewById(R.id.button_gray);
        final Button sky = (Button) dlg.findViewById(R.id.button_sky);
        final Button yellow = (Button) dlg.findViewById(R.id.button_yellow);
        final Button pupple = (Button) dlg.findViewById(R.id.button_pupple);
        final Button cyan = (Button) dlg.findViewById(R.id.button_cyan);
        final Button orange = (Button) dlg.findViewById(R.id.button_orange);
        final Button green = (Button) dlg.findViewById(R.id.button_green);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#ffffff"));
                dlg.dismiss();
            }
        });

        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor( Color.parseColor("#adadad"));
                dlg.dismiss();
            }
        });

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor( Color.parseColor("#03fcfc"));
                dlg.dismiss();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#ffff00"));
                dlg.dismiss();
            }
        });

        pupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#ff00ff"));
                dlg.dismiss();
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#8078ff"));
                dlg.dismiss();
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.parseColor("#ff8800"));
                dlg.dismiss();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor( Color.parseColor("#00ff00"));
                dlg.dismiss();
            }
        });
    }
}