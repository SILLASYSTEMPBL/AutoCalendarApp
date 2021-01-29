package com.cookandroid.calendar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class AlarmReceiver extends BroadcastReceiver {
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingI = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            System.out.println("TagLog : Version High!");
/*
            builder.setContentTitle("가까운 일정이 있습니다!");
            builder.setContentText("일정등장");
           // builder.setPriority(NotificationCompat.PRIORITY_HIGH);
           // builder.setCategory(NotificationCompat.CATEGORY_ALARM);

            String channelName ="매일 알람 채널";
            String description = "매일 정해진 시간에 알람합니다.";
            int importance = NotificationManager.IMPORTANCE_HIGH; //소리와 알림메시지를 같이 보여줌

            NotificationChannel channel = new NotificationChannel("default", channelName, importance);
            channel.setDescription(description);

            if (notificationManager != null) {
                // 노티피케이션 채널을 시스템에 등록
                notificationManager.createNotificationChannel(channel);
            }*/
        }else //{
        {System.out.println("TagLog : else is on progress");}

            builder.setSmallIcon(R.mipmap.ic_launcher);
            System.out.println("TagLog : Version Low");

            builder.setAutoCancel(false)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())

                    .setTicker("{Time to watch some cool stuff!}")
                    .setContentTitle("가까운 일정이 있습니다!")
                    .setContentText("일정을 확인하려면 클릭")
                    .setContentInfo("INFO")
                    .setContentIntent(pendingI);

            if (notificationManager != null) {

                // 노티피케이션 동작시킴
                notificationManager.notify(1234, builder.build());
        }
    }
}
