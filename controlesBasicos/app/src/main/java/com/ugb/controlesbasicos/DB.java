package com.ugb.controlesbasicos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static final String dbname = "amigos";
    private static final int v =1;
    private static final String SQLdb = "CREATE TABLE lugares(idLugar integer primary key autoincrement, " +
            "nombre text, direccion text, telefono text, descripcion text, calificacion text, precio text)";
    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //cambiar estructura de la BD
    }
    public String administrar_lugar(String accion, String[] datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if( accion.equals("nuevo") ){
                sql = "INSERT INTO lugares(nombre,direccion,telefono,descripcion,calificacion,precio) VALUES('"+ datos[1] +"','"+ datos[2] +"', '"+
                        datos[3] +"', '"+ datos[4] +"','"+ datos[5] +"','"+ datos[6] +"')";
            } else if (accion.equals("modificar")) {
                sql = "UPDATE lugares SET nombre='"+ datos[0] +"',direccion='"+ datos[1] +"',telefono='"+ datos[3] +"', descripcion='"+ datos[4] +"', calificacion='"+ datos[5] +"', precio=" +
                        "'"+ datos[6] +"' WHERE idLugar='"+ datos[2] +"'";
            } else if (accion.equals("eliminar")) {
                sql = "DELETE FROM lugares WHERE idLugar='"+ datos[2] +"'";
            }
            db.execSQL(sql);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public Cursor obtener_amigos(){
        Cursor cursor;
        SQLiteDatabase db = getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM lugares ORDER BY nombre", null);
        return cursor;
    }

}