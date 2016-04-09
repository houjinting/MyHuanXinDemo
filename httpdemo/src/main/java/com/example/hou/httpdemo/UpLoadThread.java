package com.example.hou.httpdemo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hou on 2016/4/6.
 */
public class UpLoadThread extends Thread{

    private String url;

    @Override
    public void run() {
        try {
            URL httpurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
