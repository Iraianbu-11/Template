package com.example.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Notification extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
           // showLogoutNotification();
            simpleNotification();
            //Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private void simpleNotification() {
        String channelId = "simple_channel";
        String channelName = "Simple Channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel for simple notifications");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_baseline_logout_24)
                .setContentTitle("Logout")
                .setContentText("Do you want to logout")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // Automatically dismiss the notification when clicked

        notificationManager.notify(0, mbuilder.build());
    }


    private void showLogoutNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "logout_channel";
        CharSequence channelName = "Logout Channel";
        String channelDescription = "Channel for logout notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_baseline_logout_24) // Set small icon
                .setContentTitle("Logout") // Set title
                .setContentText("Do you want to Logout") // Set message
                .setAutoCancel(true) // Automatically dismiss the notification when clicked
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Add action to log out when "Yes" is clicked
        Intent yesIntent = new Intent(this, MainActivity.class); // Replace Login.class with your actual logout activity
        yesIntent.putExtra("action", "logout");
        PendingIntent yesPendingIntent = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Yes", yesPendingIntent);

        // Add action to dismiss the notification when "No" is clicked
        Intent noIntent = new Intent(this, Set2.class); // Replace DashBoard.class with your actual activity
        noIntent.setAction("DISMISS_NOTIFICATION");
        PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "No", noPendingIntent);

        notificationManager.notify(1, builder.build());
    }
}
