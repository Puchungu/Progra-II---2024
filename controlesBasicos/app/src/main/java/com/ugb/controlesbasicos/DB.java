package com.ugb.controlesbasicos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.StringTokenizer;

public class DB extends SQLiteOpenHelper {
    private static final String dbname = "tienda";
    private static final int v =1;
    private static final String SQLdb = "CREATE TABLE tienda(idProducto integer primary key autoincrement, nombre text, descripcion text, marca text, presentacion text, precio text)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //cambiar estructura de la BD
    }



    public String administrar_productos(String accion, String[] datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if( accion.equals("nuevo") ){
                sql = "INSERT INTO tienda(nombre,descripcion,marca,presentacion,precio) VALUES('"+ datos[1] +"', '"+ datos[2] +"', '"+ datos[3] +"', " +
                        "'"+ datos[4] +"','"+ datos[5] +"')";
            } else if (accion.equals("modificar")) {
                sql = "UPDATE tienda SET nombre='"+ datos[1] +"', descripcion='"+ datos[2] +"', marca='"+ datos[3] +"', presentacion=" +
                        "'"+ datos[4] +"', precio='"+ datos[5] +"' WHERE idProducto='"+ datos[0] +"'";
            } else if (accion.equals("eliminar")) {
                sql = "DELETE FROM tienda WHERE idProducto='"+ datos[0] +"'";
            }
            db.execSQL(sql);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public Cursor obtener_producto(){
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tienda ORDER BY nombre", null);
        return cursor;
    }

}