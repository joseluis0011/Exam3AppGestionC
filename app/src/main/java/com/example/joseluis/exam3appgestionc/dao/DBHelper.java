package com.example.joseluis.exam3appgestionc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.joseluis.exam3appgestionc.models.Contactos;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "dbcontacto";
    public static final String TABLE_NAME = "contacto";
    public static final String COL0 = "ID";
    public static final String COL1 = "nombre";
    public static final String COL2 = "numero";
    public static final String COL3 = "email";
    public static final String COL4 = "perfil";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                TABLE_NAME + " ( " +
                COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /**
     * Insert a new contact into the database
     * @param contactos
     * @return
     */
    public boolean agregarContacto(Contactos contactos){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,contactos.getNombre());
        contentValues.put(COL2,contactos.getNumero());
        contentValues.put(COL3,contactos.getEmail());
        contentValues.put(COL4,contactos.getPerfil());

        long result =db.insert(TABLE_NAME,null,contentValues);
        if (result == 1){
            return  false;
        }else {
            return true;
        }
    }
    /**
     * Retrieve all contacts from database
     * @return
     */
    public Cursor getAllContacts(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public boolean updateContact(Contactos contact, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, contact.getNombre());
        contentValues.put(COL2, contact.getNumero());
        contentValues.put(COL3, contact.getEmail());
        contentValues.put(COL4, contact.getPerfil());

        int update = db.update(TABLE_NAME, contentValues, COL0 + " = ? ", new String[] {String.valueOf(id)} );

        if(update != 1) {
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Retrieve the contact unique id from the database using @param
     * @param contact
     * @return
     */
    public Cursor getContactID(Contactos contact){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME  +
                " WHERE " + COL1 + " = '" + contact.getNombre() + "'" +
                " AND " + COL2 + " = '" + contact.getNumero() + "'";
        return db.rawQuery(sql, null);
    }

    public Integer deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }
}
