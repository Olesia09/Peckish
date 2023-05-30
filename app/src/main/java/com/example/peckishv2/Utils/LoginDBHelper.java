package com.example.peckishv2.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.peckishv2.Backend.Login;

public class LoginDBHelper  extends SQLiteOpenHelper {

    public static final String DB_name = "Login_db";

    public LoginDBHelper(Context context) {
        super(context, "Login_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase LoginDB) {
        LoginDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, passwork TEXT, email TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase LoginDB, int oldVersion, int newVersion) {
    LoginDB.execSQL("DROP TABLE IF EXISTS users");

    }

    public boolean insert(String username, String password, String email)
    {
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usernmae", username);
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
}
