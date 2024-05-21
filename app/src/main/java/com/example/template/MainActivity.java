package com.example.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button set1 , set2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set1 = findViewById(R.id.set1);
        set2 = findViewById(R.id.set2);

        set1.setOnClickListener(v->{
            startActivity(new Intent(this,Set1.class));
        });

        set2.setOnClickListener(v-> {
            startActivity(new Intent(this,Set2.class));
        });

    }
}

