package com.muhsanjaved.notification_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView output_text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        output_text = findViewById(R.id.textView);

        if (getIntent().hasExtra(MainActivity.MESSAGE_KEY)){
            String message = getIntent().getStringExtra(MainActivity.MESSAGE_KEY);
            Toast.makeText(this, message+" from notification !", Toast.LENGTH_SHORT).show();
            output_text.setText(message);
        }

    }
}