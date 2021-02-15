package com.example.videostreamingtask2;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class VideoDownloadService extends Service {

    CountDownTimer cdt = null;
   // Intent bi = new Intent(COUNTDOWN_BR);

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");
        int minutes = 2;
        long millis = minutes * 60 * 1000;



    }

    @Override
    public void onDestroy() {

        cdt.cancel();
        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


}
