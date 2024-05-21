package com.example.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Whatsapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        final EditText messageEditText = findViewById(R.id.message);
        Button submit = findViewById(R.id.submit);
        EditText message = findViewById(R.id.usermsg);
        EditText phone = findViewById(R.id.userphone);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();
                sendMessage(message);
            }
        });

        findViewById(R.id.msgapp).setOnClickListener(v -> {
            Intent intents = new Intent(Intent.ACTION_VIEW);
            intents.setData(Uri.parse("sms:"));
            startActivity(intents);
        });

        findViewById(R.id.sendMsg).setOnClickListener(v -> {
            String msg = message.getText().toString();
            String phn = phone.getText().toString();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phn, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent Successfully", Toast.LENGTH_LONG).show();
        });

    }


    private void sendMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Please install whatsapp first.", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }
}
