package com.ugb.controlesbasicos;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.AdapterView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class showPlace extends AppCompatActivity {

    Bundle paramatros = new Bundle();
    ListView lts;
    Cursor cLugar;
    lugares datosLugares;
    final ArrayList<lugares> alLugar = new ArrayList<lugares>();
    final ArrayList<lugares> alLugarCopy = new ArrayList<lugares>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        obtenerLugares();

        FloatingActionButton btn = findViewById(R.id.fabreverse);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showPlace.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void obtenerLugares(){
        try{
            alLugar.clear();
            alLugarCopy.clear();
            DB db = new DB(getApplicationContext(),"", null, 1);
            cLugar = db.obtener_amigos();
            if( cLugar.moveToFirst() ){
                lts = findViewById(R.id.lts);
                do{
                    datosLugares = new lugares(
                            cLugar.getString(0),//idAmigo
                            cLugar.getString(1),//nombre
                            cLugar.getString(2),//direccion
                            cLugar.getString(3),//telefono
                            cLugar.getString(4),//descripcion
                            cLugar.getString(5),//calificacion
                            cLugar.getString(6)//calificacion
                    );
                    alLugar.add(datosLugares);
                }while (cLugar.moveToNext());
                alLugarCopy.addAll(alLugar);

                adaptadorImagenes adImagenes = new adaptadorImagenes(getApplicationContext(), alLugar);
                lts.setAdapter(adImagenes);
                registerForContextMenu(lts);
            }else {
                paramatros.putString("accion", "nuevo");
                abrirActividad(paramatros);
                mostrarMsg("No hay Datos de amigos.");
            }
        }catch (Exception e){
            mostrarMsg("Error al obtener los amigos: "+ e.getMessage());
        }
    }
    private void mostrarMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void abrirActividad(Bundle parametros){
        Intent abrirActividad = new Intent(getApplicationContext(), showPlace.class);
        abrirActividad.putExtras(parametros);
        startActivity(abrirActividad);
    }

}

