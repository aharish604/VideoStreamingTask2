package com.example.videostreamingtask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.exoplayer2.source.MediaSource;

public class SplashActivity extends AppCompatActivity {

    int SPLASH_SCREEN_TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dispsplashScreen();

    }
    public void dispsplashScreen() {

        String[] source=Constants.urls;
        MediaSource mediaSource=null;
        Constants.mediaSourceslist.clear();

        for(int i=0;i<=source.length-1;i++)
        {
            mediaSource =Constants.buildMediaSource(source[i], null,SplashActivity.this);
            Constants.mediaSourceslist.add(mediaSource);
        }

     //   Constants.displayLongToast(SplashActivity.this,"List Size:-"+Constants.mediaSourceslist.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}