package com.muhsanjaved.notification_practice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();
    public MyIntentService(String name) {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "onHandleIntent: Called");
        startForeground(1005,getNotification());
        if (intent != null){
            if (intent.hasExtra(MainActivity.MESSAGE_KEY)){
                String data = intent.getStringExtra(MainActivity.MESSAGE_KEY);
                Log.d(TAG,"onHandleIntent: Data: "+ data);

                for (int i = 0; i<10; i++){
                    Log.d(TAG, "onHandleIntent: Counter is: "+(i+1));
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private Notification getNotification(){

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                .setSmallIcon(R.drawable.two_24)
                .setContentText("Service Notification")
                .setContentText("A Background service in Running")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        return notification;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDesktop: called");
        stopForeground(true);
    }
}