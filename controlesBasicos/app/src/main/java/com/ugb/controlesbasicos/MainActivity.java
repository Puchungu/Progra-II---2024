package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    RadioGroup opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



               /* tempVal = findViewById(R.id.txtnum1);*/
               /* double num1 = Double.parseDouble(tempVal.getText().toString()); */

                /*tempVal = findViewById(R.id.txtnum2);*/
                 /*double num2 = Double.parseDouble(tempVal.getText().toString());*/

                EditText editText1 = findViewById(R.id.txtnum1);
                EditText editText2 = findViewById(R.id.txtnum2);
                String num1Str = editText1.getText().toString();
                String num2Str = editText2.getText().toString();


               double num1,num2;

                if (num1Str.isEmpty()) {
                     num1 = 0; // Asignar 0 si está vacío
                } else {
                     num1 = Double.parseDouble(num1Str);
                }

                if (num2Str.isEmpty()) {
                     num2 = 0; // Asignar 0 si está vacío
                } else {
                     num2 = Double.parseDouble(num2Str);
                }

                double respuesta = 0;
                opt = findViewById(R.id.optOpciones);
                switch (opt.getCheckedRadioButtonId()){
                    case R.id.optSuma:
                        respuesta = num1+num2;
                        break;
                    case R.id.optResta:
                        respuesta = num1-num2;
                        break;
                    case R.id.optMulplicacion:
                        respuesta = num1*num2;
                        break;
                    case R.id.optDivision:
                        respuesta = num1/num2;
                        break;
                    case R.id.optPorcentaje:
                        respuesta = (num2/100)*num1;
                        break;
                    case R.id.optFactorial:
                        long factorial = 1;
                        for (int i = 1; i <= num1; i++) {
                            factorial *= i;
                            respuesta = factorial;
                        }
                        break;
                    case R.id.optExponente:
                        respuesta = Math.pow(num1, num2);
                        break;
                    case R.id.optRaiz:
                        respuesta = Math.pow(num1, 1/num2);
                        break;
                }
                tempVal = findViewById(R.id.lblrespuesta);
                tempVal.setText("Respuesta: "+ respuesta);
            }
        });
    }
}