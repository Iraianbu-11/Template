package com.example.template;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Dialog extends AppCompatActivity {
    Button but , alert;
    TextView textView;
    Calendar calendar;
    Button progress;
    ProgressDialog pd;
    Handler handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        but = findViewById(R.id.button2);
        //but.setBackgroundColor(Color.GREEN);
        textView = findViewById(R.id.textView5);
        progress = findViewById(R.id.progress);
        alert = findViewById(R.id.alert);

        // Initialize calendar instance
        calendar = Calendar.getInstance();

        // Set OnClickListener for the button
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Datepicker dialog
                showDatePicker();
            }
        });

        handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                pd.incrementProgressBy(1);
            }
        };

        progress.setOnClickListener(v -> {
            //horizontal();
            spinner();
        });

        alert.setOnClickListener(v->{
            //showAlertDialog();
           simpleAlert();
        });
    }

    private void simpleAlert() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Exit..!!!");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_logout_24);
        alertDialogBuilder.setMessage("Are you sure,You want to exit");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) { finish(); } });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { Toast.makeText(getApplicationContext(),"You clicked over No",Toast.LENGTH_SHORT).show();
            } });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
            } }); AlertDialog alertDialog = alertDialogBuilder.create(); alertDialog.show(); }

    private void showAlertDialog() {
        final List<String> items = new ArrayList<>();
        items.add("Visit");
        items.add("View Location");
        items.add("Share");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do You Want to Logout??")
                .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform action based on the clicked item
                                String selectedItem = items.get(which);
                                Toast.makeText(getApplicationContext(), selectedItem + " clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    private void spinner() {
        pd = new ProgressDialog(this);
        pd.setMax(50);
        pd.setMessage("Its loading....");
        pd.setTitle("ProgressDialog bar example");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
            }
        }, 3000);
    }

    private void horizontal() {
        pd = new ProgressDialog(this);
        pd.setMax(100);
        pd.setMessage("Its loading....");
        pd.setTitle("ProgressDialog bar example");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (pd.getProgress() <= pd.getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (pd.getProgress() == pd.getMax()) {
                            pd.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    // Method to show Datepicker dialog
    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Set the selected date to the calendar instance
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Show Timepicker dialog after selecting the date
                showTimePicker();
            }
        }, year, month, dayOfMonth);
        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    // Method to show Timepicker dialog
    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog instance
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Set the selected time to the calendar instance
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Display the selected date and time as a toast message and in the TextView
                String selectedDateTime = "Selected Date and Time: " + calendar.getTime();
                textView.setText(selectedDateTime);
                Toast.makeText(getApplicationContext(), "Booking Successful!!!!", Toast.LENGTH_LONG).show();
            }
        }, hour, minute, false);

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
}
