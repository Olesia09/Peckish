package com.example.peckishv2.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.peckishv2.Models.ShoppingListItems;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "shoppingList_DB";
    private static final String list_table = "ingredients_to_buy";
    private static final String ID = "id";
    private static final String Item_ingredient = "item_ingredient";
    private static final String Status = "status";
    private static final String create_new_table = "CREATE TABLE " + list_table + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Item_ingredient + " TEXT, " + Status + " INTEGER)";

    private SQLiteDatabase db;
    public DataBaseManager(Context context)
    {
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(create_new_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Drop old table
        db.execSQL("DROP TABLE IF EXISTS " + list_table);

        //create new
        onCreate(db);
    }

    public void openDB()
    {
        db = this.getWritableDatabase();
    }

    public void insert_ingredient(ShoppingListItems items)
    {
        ContentValues values = new ContentValues();
        values.put(Item_ingredient, items.getIngredient());
        values.put(Status, 0);
        db.insert(list_table, null, values);
    }

    @SuppressLint("Range")
    public List<ShoppingListItems> getAllItems()
    {
        List<ShoppingListItems> itemsList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();

            try{
            cursor = db.query(list_table, null, null, null, null, null, null, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    do{
                        ShoppingListItems item = new ShoppingListItems();
                        item.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        item.setIngredient(cursor.getString(cursor.getColumnIndex(Item_ingredient)));
                        item.setStatus(cursor.getInt(cursor.getColumnIndex(Status)));
                        itemsList.add(item);
                    }while(cursor.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            assert cursor != null;
            cursor.close();
        }
        return itemsList;
    }

    public void updateStatus(int id, int status)
    {
        ContentValues values = new ContentValues();
        values.put(Status,status);
        db.update(list_table, values, ID + "=?", new String[] {String.valueOf(id)});

        //delete if checked
        if (status == 1) {
            deleteItem(id);
        }
    }

    public void updateText(int id, String item_ingredient)
    {
        ContentValues values = new ContentValues();
        values.put(Item_ingredient,item_ingredient);
        db.update(list_table, values, ID + "=?", new String[] {String.valueOf(id)});
    }

    private void deleteItem(int id) {
        db.delete(list_table, ID + "=?", new String[] {String.valueOf(id)});
    }

}