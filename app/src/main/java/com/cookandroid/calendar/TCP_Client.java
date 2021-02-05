package com.cookandroid.calendar;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_Client extends AsyncTask<String, Integer, Boolean> {
    protected static String SERV_IP = "115.136.250.144";
    protected static int PORT = 9000;
    SharedPreferences dataString;
    Uri image;
    String path;
    DataInputStream dis;
    DataInputStream dis2;
    DataOutputStream dos;
    Socket sock;
    Socket sock2;
    File file;
    String msgData = "";

    public TCP_Client(Context context, String path) {
        this.path = path;
        //dataString = getSharedPreferences("dataString",MODE_PRIVATE);
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        try {
            Log.d("TCP","server connecting");
            InetAddress serverAddr = InetAddress.getByName(SERV_IP);
            sock = new Socket(serverAddr,PORT);
            file = new File(path);
            Log.i("TagLog : path ",path);
            Log.i("TagLog : file ",file.toString());
        } catch (UnknownHostException e) {
            Log.i("TagLog : ","Unknown Host");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("TagLog : ","서버 접속 못함");
            e.printStackTrace();
        } catch (SecurityException e) {
            Log.i("TagLog : ","보안 문제 발생");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Log.i("TagLog : ","파라미터 오류");
            e.printStackTrace();
        }
        try {
            Log.i("TagLog : ", "서버 접속 완료 송신 준비");

            dis = new DataInputStream(new FileInputStream(file));
            //FileInputStream dis = new FileInputStream(file);
            // dis2 = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());

            long fileSize = file.length();
            Log.i("TagLog : size ", Long.toString(fileSize));
            byte[] buf = new byte[2048];

            long totalReadBytes = 0;
            int readBytes;

            while ((readBytes = dis.read(buf)) > 0) {
                dos.write(buf, 0, readBytes);
                totalReadBytes += readBytes;
            }

            System.out.println("TagLog : Send END " + totalReadBytes);
            dos.close();
            System.out.println("TagLog : DATA END");


//            dos = new DataOutputStream(sock.getOutputStream());
            //          dos.write('Hello!');
        } catch(IOException e) {
            Log.i("TagLog : ", "don't send message");
            e.printStackTrace();
        }
        try {
            Log.d("TCP","server2 connecting");
            InetAddress serverAddr = InetAddress.getByName(SERV_IP);
            sock2 = new Socket(serverAddr,PORT);
            file = new File(path);
            Log.i("TagLog : path ",path);
            Log.i("TagLog : file ",file.toString());
        } catch (UnknownHostException e) {
            Log.i("TagLog : ","Unknown Host");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("TagLog : ","서버 접속 못함");
            e.printStackTrace();
        } catch (SecurityException e) {
            Log.i("TagLog : ","보안 문제 발생");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Log.i("TagLog : ","파라미터 오류");
            e.printStackTrace();
        }
        try {
            Log.i("TagLog : ", "서버 접속 완료 수신 준비");
            dis2 = new DataInputStream(sock2.getInputStream());
            byte[] buf = new byte[1024];
            int readCount = dis2.read(buf);
            msgData = new String(buf,0,readCount,"UTF-8");
            dis2.close();
            Log.i("TagLog : ",msgData);
        } catch (IOException e) {
            Log.i("TagLog : ", "don't receive message");
            e.printStackTrace();
        }
        //Intent schedule_Intent = new Intent(getApplicationContext(),ScheduleActivity.class);
        //startActivity(schedule_Intent);
        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean aBoolean) {
        super.onCancelled(aBoolean);
    }
/*
    @Override
    protected void onProgressUpdate() {

    }*/
}
