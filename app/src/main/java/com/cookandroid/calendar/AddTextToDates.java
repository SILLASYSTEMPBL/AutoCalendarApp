package com.cookandroid.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class AddTextToDates implements LineBackgroundSpan{

    String InputData;
    int color;
    int linenum;
    int line_pad=12;
    //String dayPrice =InputData;
    public AddTextToDates(String data){
        InputData = data;
    }
    public AddTextToDates(int intData,int line) {color = intData;linenum = line;}

    @Override
    public void drawBackground(Canvas canvas, Paint paint, int i, int i1, int i2, int i3, int i4, CharSequence charSequence, int i5, int i6, int i7) {
        paint.setColor(color);
        paint.setStrokeWidth(6f);
        //canvas.drawText(String.valueOf(InputData),0,(i4+15),paint);
        canvas.drawLine(10,(i2+i4)/2+paint.getTextSize()+line_pad*linenum,i1-10,(i2+i4)/2+paint.getTextSize()+line_pad*linenum,paint);
    }

        /*override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: i,
        right: i1,
        top: i2,
        baseline: i3,
        bottom: i4,
        text: CharSequence,
        start: i5,
        end: i6,
        lnum: i7
        ) {*/

        }