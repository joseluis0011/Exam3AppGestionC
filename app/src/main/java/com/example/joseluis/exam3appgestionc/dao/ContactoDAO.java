package com.example.joseluis.exam3appgestionc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.joseluis.exam3appgestionc.models.Contactos;

import static com.example.joseluis.exam3appgestionc.dao.DBHelper.TABLE_NAME;

public class ContactoDAO {
    private DBHelper helper;
    private SQLiteDatabase database;
    public ContactoDAO(Context context){
        helper=new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    public Integer eliminarContacto(int id){
        return getDatabase().delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }

 /*  private Contactos Agregar(Cursor cursor){
        Contactos contactos=new Contactos(
                cursor.getInt(cursor.getColumnIndex(DBHelper.COL2)),
                cursor.getInt(cursor.getColumnIndex(DBHelper.COL3)),
                cursor.getInt(cursor.getColumnIndex(DBHelper.COL4))
        );
        return true;

   }*/
}
