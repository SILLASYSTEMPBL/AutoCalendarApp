package com.cookandroid.calendar;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class eventDialog extends AppCompatActivity {
    private Context context;

    public eventDialog(Context context) {
        this.context = context;
    }

    protected void callFunction(int year,int month,int day,int day_of_week) {
        final Dialog dlg = new Dialog(context);
        dlg.setContentView(R.layout.event_main);
        dlg.show();

        final TextView title = (TextView)dlg.findViewById(R.id.event_title);
        final TextView dayView = (TextView)dlg.findViewById(R.id.event_day);

        title.setText(day+"일");
        String dow = "";
        switch(day_of_week) {
            case 1:
                dow = "일요일";
                break;
            case 2:
                dow = "월요일";
                break;
            case 3:
                dow = "화요일";
                break;
            case 4:
                dow = "수요일";
                break;
            case 5:
                dow = "목요일";
                break;
            case 6:
                dow = "금요일";
                break;
            case 7:
                dow = "토요일";
                break;
            default:
                dow = "에러";
                break;

        }
        dayView.setText(dow);

        ScrollView scrollView = (ScrollView) dlg.findViewById(R.id.scroll);
        /*
        아무튼 DB 불러서 값 가져와서 비교하는 과정이 들어갈 곳
        */

    }
}
