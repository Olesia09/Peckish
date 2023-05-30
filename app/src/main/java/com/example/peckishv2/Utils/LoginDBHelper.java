package com.example.peckishv2.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LoginDBHelper  extends SQLiteOpenHelper {

    public static final String DB_name = "Login_db";

    public LoginDBHelper(Context context) {
        super(context, "Login_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase LoginDB) {
        LoginDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT, email TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase LoginDB, int oldVersion, int newVersion) {
    LoginDB.execSQL("DROP TABLE IF EXISTS users");

    }

    public boolean insert(String username, String password, String email)
    {
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = LoginDB.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username)
    {
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        Cursor cursor = LoginDB.rawQuery("Select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        Cursor cursor = LoginDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }

    public boolean updateDetails(String newUsername, String newPassword, String newEmail, String confirm_pass) {
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newUsername);
        contentValues.put("password", newPassword);
        contentValues.put("email", newEmail);
        int rowsAffected = LoginDB.update("users", contentValues, "password=?", new String[]{confirm_pass});
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public String getUsername()
    {
        String userName = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query the database to retrieve the user name
            String query = "SELECT username FROM users";
            cursor = db.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()) {
                userName = cursor.getString(cursor.getColumnIndex("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return userName;
    }
}
