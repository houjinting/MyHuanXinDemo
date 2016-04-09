package com.example.hou.httpdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private String path="http://img.ugirls.com/uploads/cooperate/baidu/20160401xn1.jpg";
    private ImageView iv;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        handler = new Handler();
        new HttpThread(path,iv,handler).start();
    }
}
