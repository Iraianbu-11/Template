package com.example.template.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneMode extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            if (isAirplaneModeOn) {
                Toast.makeText(context, "Airplane Mode On", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Airplane Mode Off", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
