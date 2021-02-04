package com.cookandroid.calendar;

import android.content.ContentProvider;
import android.content.ContentResolver;
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

public class TCP_Client extends AsyncTask {
    protected static String SERV_IP = "122.202.39.37";
    protected static int PORT = 9000;
    Uri image;
    String path;
    DataInputStream dis;
    DataInputStream dis2;
    DataOutputStream dos;
    Socket sock;
    Socket sock2;
    File file;
    boolean outCheacker;

    public TCP_Client(String path) {
        this.path = path;outCheacker = true;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

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
            String data = new String(buf,0,readCount,"UTF-8");
            dis2.close();
            Log.i("TagLog : ",data);
        } catch (IOException e) {
                Log.i("TagLog : ", "don't receive message");
                e.printStackTrace();
        }
        return null;
    }

}
