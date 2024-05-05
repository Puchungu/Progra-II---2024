package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView registerUserIV = findViewById(R.id.registerUser);

        // Set an onClick listener on the ImageView
        registerUserIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Register activity
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        ImageView addPlaceIV = findViewById(R.id.addPlace);
        // Set an onClick listener on the ImageView
        addPlaceIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Register activity
                Intent intent = new Intent(MainActivity.this, addPlace.class);
                startActivity(intent);
            }
        });

        ImageView showPlaceIV = findViewById(R.id.showplaces);
        // Set an onClick listener on the ImageView
        showPlaceIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Register activity
                Intent intent = new Intent(MainActivity.this, showPlace.class);
                startActivity(intent);
            }
        });

    }
}
