package com.example.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.template.broadcast.UserBroadcast;

public class Set1 extends AppCompatActivity {
    Button notification , dialog , location ,
            menuButton , message , whatsapp,
            userbroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set1);
        notification = findViewById(R.id.notification);
        dialog = findViewById(R.id.dialog);
        location = findViewById(R.id.locationBtn);
        menuButton = findViewById(R.id.menuBtn);
        message = findViewById(R.id.messageBtn);
        whatsapp = findViewById(R.id.whatsapp);
        userbroadcast = findViewById(R.id.userbroadcast);

        userbroadcast.setOnClickListener(v->{
            startActivity(new Intent(this, UserBroadcast.class));
        });

        notification.setOnClickListener(v->{
            startActivity(new Intent(this,Notification.class));
        });

        dialog.setOnClickListener(v->{
            startActivity(new Intent(this,Dialog.class));
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        });

        location.setOnClickListener(v->{
            startActivity(new Intent(this,location.class));
        });

        menuButton.setOnClickListener(v->{
            startActivity(new Intent(this,Menus.class));
        });

        message.setOnClickListener(v->{
            startActivity(new Intent(this,Messages.class));
        });

        whatsapp.setOnClickListener(v->{
            startActivity(new Intent(this,Whatsapp.class));
        });

    }
}