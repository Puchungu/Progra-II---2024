package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class listado_productos extends AppCompatActivity {
    Bundle parametros = new Bundle();
    DB db;
    ListView lts;
    Cursor cProductos;
    final ArrayList<Productos> alProductos = new ArrayList<Productos>();
    final ArrayList<Productos> alProductosCopy = new ArrayList<Productos>();
    Productos datosProductos;
    FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_productos);
        btn = findViewById(R.id.fabAgregarProductos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parametros.putString("accion","nuevo");
                abrirActividad(parametros);
            }
        });
        obtenerAmigos();
        buscarAmigos();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        cProductos.moveToPosition(info.position);
        menu.setHeaderTitle("Que deseas hacer con "+ cProductos.getString(1));//1 es el campo nombre
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try{
            switch (item.getItemId()){
                case R.id.mnxAgregar:
                    parametros.putString("accion", "nuevo");
                    abrirActividad(parametros);
                    break;
                case R.id.mnxModificar:
                    String[] amigos = {
                            cProductos.getString(0), //idAmigo
                            cProductos.getString(1), //nombre
                            cProductos.getString(2), //direccion
                            cProductos.getString(3), //telefono
                            cProductos.getString(4), //email
                            cProductos.getString(5), //dui
                            cProductos.getString(6), //foto
                    };
                    parametros.putString("accion", "modificar");
                    parametros.putStringArray("amigos", amigos);
                    abrirActividad(parametros);
                    break;
                case R.id.mnxEliminar:
                    eliminarAmigo();
                    break;
            }
            return true;
        }catch (Exception e){
            mostrarMsg("Error al seleccionar el item: "+ e.getMessage());
            return super.onContextItemSelected(item);
        }
    }
    private void eliminarAmigo(){
        try {
            AlertDialog.Builder confirmar = new AlertDialog.Builder(listado_productos.this);
            confirmar.setTitle("Esta seguro de eliinar: ");
            confirmar.setMessage(cProductos.getString(1));
            confirmar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String respuesta = db.administrar_productos("eliminar", new String[]{cProductos.getString(0)});
                    if( respuesta.equals("ok") ){
                        mostrarMsg("item eliminado con exito");
                        obtenerAmigos();
                    }else{
                        mostrarMsg("Error al eliminar el item: "+ respuesta);
                    }
                }
            });
            confirmar.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            confirmar.create().show();
        }catch (Exception e){
            mostrarMsg("Error al eliminar: "+ e.getMessage());
        }
    }
    private void buscarAmigos(){
        TextView tempVal = findViewById(R.id.txtBuscarProductos);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    alProductos.clear();
                    String valor = tempVal.getText().toString().trim().toLowerCase();
                    if( valor.length()<=0 ){
                        alProductos.addAll(alProductosCopy);
                    }else{
                        for (Productos amigo : alProductosCopy){
                            String nombre = amigo.getNombre();
                            String direccion = amigo.getDescripcion();
                            String tel = amigo.getMarca();
                            String email = amigo.getPresentacion();
                            if( nombre.trim().toLowerCase().contains(valor) ||
                                    direccion.trim().toLowerCase().contains(valor) ||
                                    tel.trim().contains(valor) ||
                                    email.trim().toLowerCase().contains(valor)){
                                alProductos.add(amigo);
                            }
                        }
                        adaptadorImagenes adImagenes=new adaptadorImagenes(getApplicationContext(), alProductos);
                        lts.setAdapter(adImagenes);
                    }
                }catch (Exception e){
                    mostrarMsg("Error al buscar: "+ e.getMessage());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void abrirActividad(Bundle parametros){
        Intent abrirActividad = new Intent(getApplicationContext(), MainActivity.class);
        abrirActividad.putExtras(parametros);
        startActivity(abrirActividad);
    }
    private void obtenerAmigos(){
        try{
            alProductos.clear();
            alProductosCopy.clear();

            db = new DB(getApplicationContext(),"", null, 1);
            cProductos = db.obtener_producto();
            if( cProductos.moveToFirst() ){
                lts = findViewById(R.id.ltsProductos);
                do{
                    datosProductos = new Productos(
                            cProductos.getString(0),//idAmigo
                            cProductos.getString(1),//nombre
                            cProductos.getString(2),//direccion
                            cProductos.getString(3),//telefono
                            cProductos.getString(4),//email
                            cProductos.getString(5),//dui
                            cProductos.getString(6)//foto
                    );
                    alProductos.add(datosProductos);
                }while (cProductos.moveToNext());
                alProductosCopy.addAll(alProductos);

                adaptadorImagenes adImagenes = new adaptadorImagenes(getApplicationContext(), alProductos);
                lts.setAdapter(adImagenes);

                registerForContextMenu(lts);
            }else {
                parametros.putString("accion", "nuevo");
                abrirActividad(parametros);
                mostrarMsg("No hay Datos de productos.");
            }
        }catch (Exception e){
            mostrarMsg("Error al obtener los productos: "+ e.getMessage());
        }
    }
    private void mostrarMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
