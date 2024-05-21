package com.example.template;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages extends AppCompatActivity {
    Cursor cursor;
    Button button;
    private EditText editTextPhone1, editTextPhone2, editTextPhone3, editTextMessage;
    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

//        button = findViewById(R.id.Button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getContacts();
//            }
//        });


//        editTextPhone1 = findViewById(R.id.editTextPhone1);
//        editTextPhone2 = findViewById(R.id.editTextPhone2);
//        editTextPhone3 = findViewById(R.id.editTextPhone3);
//        editTextMessage = findViewById(R.id.editTextMessage);
//        buttonSend = findViewById(R.id.buttonSend);
//
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phone1 = editTextPhone1.getText().toString();
//                String phone2 = editTextPhone2.getText().toString();
//                String phone3 = editTextPhone3.getText().toString();
//                String message = editTextMessage.getText().toString();
//
//                // Send message after 30 seconds
//                sendDelayedSMS(new String[]{phone1, phone2, phone3}, message, 5);
//            }
//        });
    }


    public void getContacts() {
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);

        final List<String> selectedContactNumbers = new ArrayList<>();
        final List<String> selectedContactNames = new ArrayList<>();
        final Map<String, String> contactMap = new HashMap<>();

        while (cursor.moveToNext()) {
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactMap.put(contactName, contactNumber);
            selectedContactNames.add(contactName);
        }

        final CharSequence[] items = selectedContactNames.toArray(new CharSequence[selectedContactNames.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Contacts");

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String contactName = items[which].toString();
                if (isChecked) {
                    String contactNumber = contactMap.get(contactName);
                    selectedContactNumbers.add(contactNumber);
                } else {
                    selectedContactNumbers.remove(contactMap.get(contactName));
                }
            }
        });

        builder.setPositiveButton("Send SMS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                for (String contactNumber : selectedContactNumbers) {
                    sendSMS(contactNumber, "This is a test message.");
                }
            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent to " + phoneNumber, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed to send.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    // Schedule Messages

    private void sendDelayedSMS(String[] phoneNumbers, String message, int delaySeconds) {
        // Convert delaySeconds to milliseconds
        long delayMillis = delaySeconds * 1000;

        // Create PendingIntent for the BroadcastReceiver to handle the sending of SMS
        Intent intent = new Intent(getApplicationContext(), SmsBroadcastReceiver.class);
        intent.putExtra("phoneNumbers", phoneNumbers);
        intent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule the SMS sending
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get SmsManager instance
                    SmsManager smsManager = SmsManager.getDefault();

                    // Send SMS to each phone number
                    for (String phoneNumber : phoneNumbers) {
                        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    }

                    Toast.makeText(getApplicationContext(), "Messages sent successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed to send messages: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, delayMillis);
    }





}


