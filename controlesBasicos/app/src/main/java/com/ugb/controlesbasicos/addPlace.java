package com.ugb.controlesbasicos;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class addPlace extends AppCompatActivity {


    private EditText nombreTxt;
    private EditText direccionTxt;
    private EditText telefonoTxt;
    private EditText descripcionTxt;
    private EditText calificacionTxt;
    private EditText precioTxt;
    private Button publicarBtn;
    private FloatingActionButton reverseBtn;
    String id="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplace);
         nombreTxt = findViewById(R.id.name);
         direccionTxt = findViewById(R.id.address);
         telefonoTxt = findViewById(R.id.phone);
         descripcionTxt = findViewById(R.id.descripcion);
         calificacionTxt = findViewById(R.id.calificacion);
         precioTxt = findViewById(R.id.precio);
         publicarBtn = findViewById(R.id.share);
         reverseBtn = findViewById(R.id.reverseBtn);
         //boton atras
        reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPlace.this, MainActivity.class);
                startActivity(intent);
            }
        });

            publicarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //getText
                    String nombre = nombreTxt.getText().toString();
                    String direccion = direccionTxt.getText().toString();
                    String telefono = telefonoTxt.getText().toString();
                    String descripcion = descripcionTxt.getText().toString();
                    String calificacion = calificacionTxt.getText().toString();
                    String precio = precioTxt.getText().toString();
                    //check if empty
                    if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || descripcion.isEmpty() || calificacion.isEmpty() || precio.isEmpty()) {
                        Toast.makeText(addPlace.this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        //add to fireabase database
                        addPlaceToDatabase(nombre, direccion, telefono, descripcion, calificacion, precio);
                        AddPlacetoSQLite(nombre, direccion, telefono, descripcion, calificacion, precio);
                    }
                }
            });
    }

    //funcion para agregar un lugar a la base de datos FIREBASE
    private void addPlaceToDatabase(String nombre, String direccion, String telefono, String descripcion, String calificacion, String precio) {
        HashMap<String, Object> PlaceHashMap = new HashMap<>();
        PlaceHashMap.put("nombre", nombre);
        PlaceHashMap.put("direccion", direccion);
        PlaceHashMap.put("telefono", telefono);
        PlaceHashMap.put("descripcion", descripcion);
        PlaceHashMap.put("calificacion", calificacion);
        PlaceHashMap.put("precio", precio);
        //initiate database connection
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://turismosv-82e30-default-rtdb.firebaseio.com/");
        DatabaseReference lugarReference = databaseReference.child("Lugares");
        String key = lugarReference.push().getKey();
        PlaceHashMap.put("key", key);
        lugarReference.child(key).setValue(PlaceHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              Toast.makeText(addPlace.this, "Lugar publicado", Toast.LENGTH_SHORT).show();
                // Clear the EditText fields
                nombreTxt.setText("");
                direccionTxt.setText("");
                telefonoTxt.setText("");
                descripcionTxt.setText("");
                calificacionTxt.setText("");
                precioTxt.setText("");
            }
        });
    }

    private void AddPlacetoSQLite(String nombre, String direccion, String telefono, String descripcion, String calificacion, String precio) {
        //Logica para agregar un lugar a la base de datos SQLite
        DB db = new DB(getApplicationContext(), "", null, 1);
        String[] datos = new String[]{id, nombre, direccion, telefono, descripcion, calificacion, precio};
        String result = db.administrar_lugar("nuevo", datos);
        if (result.equals("ok")) {
            Toast.makeText(addPlace.this, "Lugar guardado en SQLite", Toast.LENGTH_SHORT).show();
            nombreTxt.setText("");
            direccionTxt.setText("");
            telefonoTxt.setText("");
            descripcionTxt.setText("");
            calificacionTxt.setText("");
            precioTxt.setText("");
        } else {
            Toast.makeText(addPlace.this, "Error al guardar el lugar en SQLite: " + result, Toast.LENGTH_SHORT).show();
        }
    }
}


