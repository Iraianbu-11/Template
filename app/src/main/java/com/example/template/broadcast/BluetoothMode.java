package com.example.template.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothMode extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED)) {
            int state = intent.getIntExtra(android.bluetooth.BluetoothAdapter.EXTRA_STATE, -1);
            switch (state) {
                case android.bluetooth.BluetoothAdapter.STATE_OFF:

                    Toast.makeText(context, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
                    break;
                case android.bluetooth.BluetoothAdapter.STATE_ON:
                    Toast.makeText(context, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
