package com.example.mobiledevelopmentapp_b00686672;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table sql query
        String CREATE_USER_TABLE;
        CREATE_USER_TABLE = "create table user (userId integer primary key autoincrement, userUsername  text, " +
                "userPassword  text, userName text, userEmail text, userGender text );";
        try {
            db.execSQL(CREATE_USER_TABLE);
        } catch (Exception er) {
            Log.e("Error : ", "error: unable to create table in database");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Update the table if already there
        db.execSQL("DROP TABLE IF EXISTS " + "user");
        //New table is created.
        onCreate(db);
    }
}
