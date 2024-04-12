package com.ugb.controlesbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class paginaprincipal extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginaprincipal);
        btn = findViewById(R.id.AgregarProd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActividad();
            }
        });
    }

    private void abrirActividad(){
        Intent abrirActividad = new Intent(getApplicationContext(), lista_amigos.class);
        startActivity(abrirActividad);
    }
}
