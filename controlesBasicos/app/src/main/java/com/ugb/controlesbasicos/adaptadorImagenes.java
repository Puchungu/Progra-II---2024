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
    ArrayList<turismo> datosTurismoArrayList;
    turismo datosTurismo;
    LayoutInflater layoutInflater;
    public adaptadorImagenes(Context context, ArrayList<turismo> datosTurismoArrayList) {
        this.context = context;
        this.datosTurismoArrayList = datosTurismoArrayList;
    }
    @Override
    public int getCount() {
        return datosTurismoArrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return datosTurismoArrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i; //Long.parseLong(datosTurismoArrayList.get(i).getIdLugar());
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes, viewGroup, false);
        try{
            datosTurismo = datosTurismoArrayList.get(i);

            TextView tempVal = itemView.findViewById(R.id.lblnombre);
            tempVal.setText(datosTurismo.getNombre());

            tempVal = itemView.findViewById(R.id.lbltelefono);
            tempVal.setText(datosTurismo.getDireccion());

            tempVal = itemView.findViewById(R.id.lblemail);
            tempVal.setText(datosTurismo.getTelefono());

            Bitmap imageBitmap = BitmapFactory.decodeFile(datosTurismo.getUrlFotoAmigo());
            ImageView img = itemView.findViewById(R.id.imgFoto);
            img.setImageBitmap(imageBitmap);
        }catch (Exception e){
            Toast.makeText(context, "Error al mostrar los datos: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return itemView;
    }
}