package com.example.finalprojectforjavaandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MydataBase extends SQLiteOpenHelper {
    private static final String NAME = "Register.db";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "signUp";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private final Context context;
    public MydataBase(Context context) {
        super(context, NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+
                " ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_USERNAME+" TEXT, "+
                COLUMN_EMAIL+" TEXT, "+
                COLUMN_PASSWORD+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query="DROP TABLE IF EXISTS "+TABLE_NAME;
    db.execSQL(query);
    onCreate(db);
    }
    public long insertData(sign_up sign){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_USERNAME,sign.getUsername());
        cv.put(COLUMN_EMAIL,sign.getEmail());
        cv.put(COLUMN_PASSWORD,sign.getPassword());
        long data=db.insert(TABLE_NAME,null,cv);
        return data;
    }
    public ArrayList<sign_up> getAllData() {
        ArrayList<sign_up> userData = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndexOrThrow(COLUMN_ID));
                String username = c.getString(c.getColumnIndexOrThrow(COLUMN_USERNAME));
                String email = c.getString(c.getColumnIndexOrThrow(COLUMN_EMAIL));
                String password = c.getString(c.getColumnIndexOrThrow(COLUMN_PASSWORD));
                userData.add(new sign_up(username, email, password,id));
            } while (c.moveToNext());
        }
        c.close();
        return userData;
    }
}
