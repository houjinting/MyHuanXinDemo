package com.example.hou.httpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by hou on 2016/4/6.
 */
public class HttpThread extends Thread {

    private String path;
    private ImageView iv;
    private Handler handler;

    HttpThread(String path, ImageView iv, Handler handler) {
        this.path = path;
        this.iv = iv;
        this.handler = handler;
    }

    public byte[] doGet() {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[4 * 1024];
            int len;
            String storageState = Environment.getExternalStorageState();
            if (!storageState.equals(Environment.MEDIA_MOUNTED)) {
                return null;
            }

            File directory = Environment.getExternalStorageDirectory();
            String filename = directory.getAbsolutePath() + File.separator + "mm.jpg";
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void doPost() {

    }

    @Override
    public void run() {
//        doGet();
        final byte[] data = doGet();
//        Log.i("123", data.length + "-----------------------------------");
//        final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//        String storageState = Environment.getExternalStorageState();
//        if(!storageState.equals(Environment.MEDIA_MOUNTED)){
//            return;
//        }
//
//        File directory = Environment.getExternalStorageDirectory();
//        String filename = directory.getAbsolutePath()+File.separator+"ss.jpg";
//        File file = new File(filename);
//        try {
//            FileOutputStream fo = new FileOutputStream(file);
//            fo.write(data);
//
//            fo.flush();
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        handler.post(new Runnable() {
            @Override
            public void run() {
               Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                iv.setImageBitmap(bitmap);
            }
        });
    }
}
