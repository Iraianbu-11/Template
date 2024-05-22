package com.example.template;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] phoneNumbers = intent.getStringArrayExtra("phoneNumbers");
        String message = intent.getStringExtra("message");
        // Get SmsManager instance
        SmsManager smsManager = SmsManager.getDefault();
        // Send SMS to each phone number
        for (String phoneNumber : phoneNumbers) {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }
        Toast.makeText(context, "Messages sent successfully", Toast.LENGTH_SHORT).show();
    }
}
