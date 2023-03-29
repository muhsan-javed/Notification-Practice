package com.muhsanjaved.notification_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID);

            builder.setSmallIcon(R.drawable.one)
                    .setContentText(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

                    .build();

            manager.notify(1,builder.build());
        });

        btnTwo.setOnClickListener(v -> {

            String title = edTitle.getText().toString();
            String message = edMessage.getText().toString();

            Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                    .setSmallIcon(R.drawable.two_24)
                    .setContentText(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    .build();

            manager.notify(1,notification);
        });
    }
}