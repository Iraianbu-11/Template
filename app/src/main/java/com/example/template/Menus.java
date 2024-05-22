package com.example.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Menus extends AppCompatActivity {
    ListView list;
    Button but;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
            case R.id.search:
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }


    String[] names = {"A","B","C"};
    Button showDialogButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        list = findViewById(R.id.listview1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        list.setAdapter(arrayAdapter);
        registerForContextMenu(list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MyApp");
        actionBar.setSubtitle("Actionbar Items");
        // actionBar.setIcon();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        but = findViewById(R.id.button4);
        but.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(this, v);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu , popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(this, item.getTitle() + " Clicked", Toast.LENGTH_SHORT).show();
                return true;
            });
            popupMenu.show();
        });

        showDialogButton = findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.user_dialog, null);

        // Create the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Get references to the EditText and Button
        EditText editText1 = dialogView.findViewById(R.id.editText1);
        EditText editText2 = dialogView.findViewById(R.id.editText2);
        Button buttonYes = dialogView.findViewById(R.id.buttonYes);

        // Set click listener for the Yes button
        buttonYes.setOnClickListener(v -> {
            String value1 = editText1.getText().toString().trim();
            String value2 = editText2.getText().toString().trim();
            Toast.makeText(Menus.this, "Value 1: " + value1 + ", Value 2: " + value2, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
    }
}