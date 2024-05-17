package com.ugb.controlesbasicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    private static final String dbname = "TurismoSV";
    private static final int v =2;

    private static final String TABLE_USUARIOS = "usuarios";
    private static final String SQLdb = "CREATE TABLE turismo(id text, rev text, idLugar text, " +
            "nombre text, descripcion text, direccion text, telefono text, precio text, foto text)";

    private static final String CREATEUSUARIOS = "CREATE TABLE usuarios(id integer PRIMARY KEY autoincrement, username TEXT, password TEXT)";

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);
        sqLiteDatabase.execSQL(CREATEUSUARIOS);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " (USERNAME, PASSWORD) VALUES ('luis', '1234')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //cambiar estructura de la BD
    }
    public String administrar_amigos(String accion, String[] datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if( accion.equals("nuevo") ){
                sql = "INSERT INTO turismo(id,rev,idLugar,nombre,descripcion,direccion,telefono,precio,foto) VALUES('"+ datos[0] +"','"+ datos[1] +"','"+ datos[2] +"', '"+
                        datos[3] +"', '"+ datos[4] +"','"+ datos[5] +"','"+ datos[6] +"', '"+ datos[7] +"', '"+ datos[8] +"' )";
            } else if (accion.equals("modificar")) {
                sql = "UPDATE turismo SET id='"+ datos[0] +"',rev='"+ datos[1] +"',nombre='"+ datos[3] +"', descripcion='"+ datos[4] +"', direccion='"+ datos[5] +"', telefono=" +
                        "'"+ datos[6] +"', precio='"+ datos[7] +"', foto='"+ datos[8] +"' WHERE idLugar='"+ datos[2] +"'";
            } else if (accion.equals("eliminar")) {
                sql = "DELETE FROM turismo WHERE idLugar='"+ datos[2] +"'";
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
        cursor = db.rawQuery("SELECT * FROM turismo ORDER BY nombre", null);
        return cursor;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE USERNAME=? AND PASSWORD=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}