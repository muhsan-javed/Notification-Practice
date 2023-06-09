package com.muhsanjaved.notification_practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null){
            if (intent.hasExtra(MainActivity.MESSAGE_KEY)){
                String message = intent.getStringExtra(MainActivity.MESSAGE_KEY);
                Toast.makeText(context, message+ " From broadCast receiver", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
