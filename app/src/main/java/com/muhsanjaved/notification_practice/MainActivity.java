package com.muhsanjaved.notification_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    EditText edTitle, edMessage;
    Button btnOne, btnTwo;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTitle = findViewById(R.id.edTitle);
        edMessage = findViewById(R.id.edMessage);
        btnOne = findViewById(R.id.button);
        btnTwo = findViewById(R.id.button2);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnOne.setOnClickListener(v -> {

            String title = edTitle.getText().toString();
            String message = edMessage.getText().toString();

            Intent activityIntent = new Intent(this,MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent,
                    PendingIntent.FLAG_MUTABLE);

            Intent actionActivityIntent = new Intent(this,SecondActivity.class);
            actionActivityIntent.putExtra(MESSAGE_KEY,message);

            PendingIntent actionPendingIntent = PendingIntent.getActivity(this,0,
                    actionActivityIntent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID);

            builder.setSmallIcon(R.drawable.one)
                    .setContentText(title)
                    .setContentText(message)
                    .setContentIntent(contentIntent)
                    .addAction(R.mipmap.ic_launcher,"Action 1 ", actionPendingIntent)
                    .addAction(R.mipmap.ic_launcher,"Action 2 ", null)
                    .addAction(R.mipmap.ic_launcher,"Action 3 ", null)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.GREEN)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();

            manager.notify(1,builder.build());
        });

        btnTwo.setOnClickListener(v -> {

            String title = edTitle.getText().toString();
            String message = edMessage.getText().toString();

            // broadcast
            Intent broadcastIntent = new Intent(this, NotificationBroadcastReceiver.class);
            broadcastIntent.putExtra(MESSAGE_KEY, message);

            PendingIntent broadcastPendingIntent = PendingIntent.getBroadcast(this,0,
                    broadcastIntent, PendingIntent.FLAG_IMMUTABLE);

            // Service
            Intent serviceIntent = new Intent(this, MyIntentService.class);
            broadcastIntent.putExtra(MESSAGE_KEY, message);

            PendingIntent servicePendingIntent = PendingIntent.getService(this,0,
                    serviceIntent, PendingIntent.FLAG_IMMUTABLE);

            Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                    .setSmallIcon(R.drawable.two_24)
                    .setContentText(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .addAction(R.mipmap.ic_launcher, "Show Toast", broadcastPendingIntent)
                    .addAction(R.mipmap.ic_launcher, "Star Service", servicePendingIntent)
                    .build();

            manager.notify(1,notification);
        });
    }
}