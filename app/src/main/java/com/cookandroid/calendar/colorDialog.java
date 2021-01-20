package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class colorDialog {
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
                Toast.makeText(context,"BLACK",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"RED",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"BLUE",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"YELLOW",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        pupple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"PUPPLE",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        cyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"CYAN",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"ORANGE",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"GREEN",Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }
}
