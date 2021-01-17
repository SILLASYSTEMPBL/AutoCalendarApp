package com.cookandroid.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class AddTextToDates implements LineBackgroundSpan{

    String InputData;
    //String dayPrice =InputData;
    public AddTextToDates(String data){
        InputData = data;
    }


    @Override
    public void drawBackground(Canvas canvas, Paint paint, int i, int i1, int i2, int i3, int i4, CharSequence charSequence, int i5, int i6, int i7) {
        paint.setColor(Color.BLACK);
        canvas.drawText(String.valueOf(InputData),0,(i4+15),paint);
    }

        /*override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
        ) {*/

        }