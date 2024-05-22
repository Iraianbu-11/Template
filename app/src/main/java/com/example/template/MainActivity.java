package com.example.template;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.template.broadcast.AirplaneMode;
import com.example.template.broadcast.BluetoothMode;
import com.example.template.broadcast.BootReceiver;
import com.example.template.broadcast.MyReceiver;

public class MainActivity extends AppCompatActivity {
    Button set1;
    AirplaneMode airplaneMode = new AirplaneMode();
    BluetoothMode bluetoothMode = new BluetoothMode();
    BatteryLevel batteryLevel = new BatteryLevel();
    BootReceiver bootReceiver = new BootReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set1 = findViewById(R.id.set1);
        set1.setOnClickListener(v->{
            startActivity(new Intent(this,Set1.class));
        });

        // User Broadcast Message

        IntentFilter intentFilter = new IntentFilter("com.example.broadcast");
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter);
    }

    protected void onStart() {
        super.onStart();
        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter batteryfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter bluetoothfilter = new IntentFilter(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter bootFilter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(airplaneMode, airplaneFilter);
        registerReceiver(batteryLevel, batteryfilter);
        registerReceiver(bluetoothMode,bluetoothfilter);
        registerReceiver(bootReceiver,bootFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneMode);
        unregisterReceiver(bluetoothMode);
        unregisterReceiver(batteryLevel);
        unregisterReceiver(bootReceiver);
    }

    private class BatteryLevel extends BroadcastReceiver {
        private final static String BATTERY_LEVEL = "level";

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BATTERY_LEVEL, 0);
            //Toast.makeText(context, "Battery Level is" + level, Toast.LENGTH_SHORT).show();
            // progressBar.setProgress(level);
            // batteryText.setText("Battery Level is "+  level);
        }
    }
}

