package com.example.mobiledevelopmentapp_b00686672;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoginDatabaseAdapter {
    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "login.db";

    // Variable to hold the database instance
    private static SQLiteDatabase db;
    // Variable to hold the database instance
    private static DataBaseHelper dbHelper;

    public LoginDatabaseAdapter(Context context) {
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public LoginDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close() {
        db.close();
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // method to get the password  of userName
    public String getSinlgeEntry(String userName) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("user", null, "userUsername=?",
                new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            Log.d("USERNAME NOT IN RECORDS", "USERNAME NOT EXIST");
            return "NOT EXIST";
        }

        cursor.moveToFirst();
        String getPassword = cursor.getString(cursor.getColumnIndex("userPassword"));
        return getPassword;
    }

    public void insertEntry(String username, String password, String name, String email, String gender) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("userUsername", username);
        newValues.put("userPassword", password);
        newValues.put("userName", name);
        newValues.put("userEmail", email);
        newValues.put("userGender", gender);

        // Insert the row into your table
        db.insert("user", null, newValues);
    }

}
