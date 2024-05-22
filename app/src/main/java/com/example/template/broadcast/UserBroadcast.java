package com.example.template.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.template.R;

public class UserBroadcast extends AppCompatActivity {

    private int clickCounter = 0;
    private static final int CLICK_THRESHOLD = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_broadcast);

        findViewById(R.id.button3).setOnClickListener(v -> {
            clickCounter+=500;
            if (clickCounter == CLICK_THRESHOLD) {
                Intent intent = new Intent()
                        .setAction("com.example.broadcast")
                        .setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
                clickCounter = 0;
            }
        });
    }
}
