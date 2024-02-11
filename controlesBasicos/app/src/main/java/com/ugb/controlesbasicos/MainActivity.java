package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

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
        tbh.addTab(tbh.newTabSpec("LON").setIndicator("LONGITUD", null).setContent(R.id.tabLogitud));
        tbh.addTab(tbh.newTabSpec("MON").setIndicator("MONEDAS", null).setContent(R.id.tabMonedas));
        tbh.addTab(tbh.newTabSpec("ALM").setIndicator("ALMACENAMIENTO", null).setContent(R.id.tabAlmacenamiento));

        btn = findViewById(R.id.btnCalcularLongitud);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeLongitud);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnALongitud);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadLongitud);
                double cantidad = Double.parseDouble(tempVal.getText().toString());
                double resp = objConversor.convertir(0, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }
        });

        btn = findViewById(R.id.btnCalcularMoneda);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeMoneda);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAMoneda);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadMoneda);
                double cantidad = Double.parseDouble(tempVal.getText().toString());
                double resp = objConversor.convertir(1, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }
        });

        btn = findViewById(R.id.btnCalcularAlmacenamiento);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(R.id.spnDeAlmacenamiento);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAAlmacenamiento);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtCantidadAlmacenamiento);
                double cantidad = Double.parseDouble(tempVal.getText().toString());
                double resp = objConversor.convertir(2, de, a, cantidad);

                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }
        });
    }

    class conversores {
        double[][] valores = {
                {1, 100, 39.3701, 3.28084, 1.193, 1.09361, 0.001, 0.000621371}, /*longitud*/
                {1.0, 0.84, 0.73, 110.59, 1.27, 0.92, 1.31, 1.38, 20.08, 6.35},  /*monedas*/
                {1000000, 1000, 1.0, 0.001, 0.000001, 0.0000000001, 0.0000000000001, 0.0000000000000001},

        };

        public double convertir(int opcion, int de, int a, double cantidad) {
            return valores[opcion][a] / valores[opcion][de] * cantidad;
        }


    }
}