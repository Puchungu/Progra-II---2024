package com.ugb.controlesbasicos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adaptadorImagenes extends BaseAdapter {
    Context context;
    ArrayList<lugares> datosLugaresArrayList;
    lugares datosLugar;
    LayoutInflater layoutInflater;
    public adaptadorImagenes(Context context, ArrayList<lugares> datosAmigosArrayList) {
        this.context = context;
        this.datosLugaresArrayList = datosAmigosArrayList;
    }
    @Override
    public int getCount() {
        return datosLugaresArrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return datosLugaresArrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return Long.parseLong(datosLugaresArrayList.get(i).getIdLugar());
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_item, viewGroup, false);
        try{
            datosLugar = datosLugaresArrayList.get(i);

            TextView tempVal = itemView.findViewById(R.id.nombreTextView);
            tempVal.setText(datosLugar.getNombre());

            tempVal = itemView.findViewById(R.id.calificacionTextView);
            tempVal.setText(datosLugar.getCalificacion());

            tempVal = itemView.findViewById(R.id.telefonoTextView);
            tempVal.setText(datosLugar.getTelefono());

        }catch (Exception e){
            Toast.makeText(context, "Error al mostrar los datos: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return itemView;
    }
}