package com.ugb.controlesbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class principal extends AppCompatActivity {

    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        btn = findViewById(R.id.publicarbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActividad = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(abrirActividad);
            }
        });


        btn = findViewById(R.id.verbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActividad = new Intent(getApplicationContext(), lista_turismo.class);
                startActivity(abrirActividad);
            }
        });

        btn = findViewById(R.id.closebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirActividad = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(abrirActividad);
            }
        });




    }


    private void abrirActividad(){
        Intent abrirActividad = new Intent(getApplicationContext(), lista_turismo.class);
        startActivity(abrirActividad);
    }
}
