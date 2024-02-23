package com.ugb.controlesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
TabHost tbh;
TextView tempVal;
Spinner spn;
Button btn;
conversores objConversor = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();
        tbh.addTab(tbh.newTabSpec("AGUA").setIndicator("Agua", null).setContent(R.id.tabTarifaxmetros));
        tbh.addTab(tbh.newTabSpec("ARE").setIndicator("Area", null).setContent(R.id.ConversorDeArea));


        btn = findViewById(R.id.btnCalcularAgua);
        btn.setOnClickListener(new View.OnClickListener() {
            @ Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtCantidadAPagar);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double cuotafija = 6.0;
                double tasasExceso = 0.45;

                double sobreExceso = 0.45;

                double valorPagar;

                if (cantidad <= 18) {
                    valorPagar = cuotafija;
                } else if (cantidad <= 28) {
                    double exceso = cantidad - 18;
                    double cargoExceso = exceso * tasasExceso;
                    valorPagar = cuotafija + cargoExceso;
                } else {
                    double exceso = cantidad - 20;
                    double cargoExceso = exceso * sobreExceso;
                    valorPagar = cuotafija * cargoExceso;
                }
                Toast.makeText(getApplicationContext(), "Respuesta: " + valorPagar, Toast.LENGTH_LONG).show();
            }
        });


        //botonMonedas
        btn = findViewById(R.id.btnCalcularArea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeArea);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAArea);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadArea);

                double cantidad = Double.parseDouble(tempVal.getText().toString());
                double resp = objConversor.convertir(0, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }
        });
    }



    class conversores {
        double[][] valores = {
                {1,10.763,1.43,1.19599,0.0022883295194508,0.000143082804880,0.0001}
        };

        public double convertir(int opcion, int de, int a, double cantidad) {
            return valores[opcion][a] / valores[opcion][de] * cantidad;
        }


    }
}
