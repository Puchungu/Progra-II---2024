package com.ugb.controlesbasicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class listado_amigos extends Activity {
    FloatingActionButton fabAgregarAmigos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_amigos);
        fabAgregarAmigos = findViewById(R.id.fabAgregarAmigos);
        fabAgregarAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAmigos = new Intent(listado_amigos.this, MainActivity.class);
                startActivity(abrirAmigos);
            }
        });
    }
}
