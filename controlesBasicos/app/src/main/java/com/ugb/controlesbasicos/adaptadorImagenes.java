package com.ugb.controlesbasicos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adaptadorImagenes extends BaseAdapter {
    Context context;
    ArrayList<Productos> datosProductosArrayList;
    Productos datosProductos;
    LayoutInflater layoutInflater;
    public adaptadorImagenes(Context context, ArrayList<Productos> datosProductosArrayList) {
        this.context = context;
        this.datosProductosArrayList = datosProductosArrayList;
    }
    @Override
    public int getCount() {
        return datosProductosArrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return datosProductosArrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return Long.parseLong(datosProductosArrayList.get(i).getIdProd());
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes, viewGroup, false);
        try{
            datosProductos = datosProductosArrayList.get(i);

            TextView tempVal = itemView.findViewById(R.id.lblnombre);
            tempVal.setText(datosProductos.getNombre());

            tempVal = itemView.findViewById(R.id.lblDescripcion);
            tempVal.setText(datosProductos.getDescripcion());

            tempVal = itemView.findViewById(R.id.lblMarca);
            tempVal.setText(datosProductos.getMarca());

            tempVal = itemView.findViewById(R.id.lblPresentacion);
            tempVal.setText(datosProductos.getPresentacion());

            tempVal = itemView.findViewById(R.id.lblPrecio);
            tempVal.setText(datosProductos.getPrecio());
        }catch (Exception e){
            Toast.makeText(context, "Error al mostrar los datos: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return itemView;
    }
}
