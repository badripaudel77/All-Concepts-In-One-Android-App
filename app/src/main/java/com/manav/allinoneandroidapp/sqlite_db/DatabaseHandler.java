package com.manav.allinoneandroidapp.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    //fields for the items
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEM_DESC = "description";
    public static final String COLUMN_ID = "id";

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1; //change db version if you change column or something

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Database creation sql statement
        String SQL_ITEMS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_ITEMS +
                "(" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_ITEM_DESC + " text not null);";

        db.execSQL(SQL_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public boolean addItem(String description) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_DESC, description);

        long result = database.insert(TABLE_ITEMS, null, values);
        return result !=-1; // if successful result will not be -1
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<>();
        String desc;
        int id;

        String fetchQuery = "SELECT * FROM " + TABLE_ITEMS;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(fetchQuery, null);
        if(cursor.moveToFirst()) {

            do {
                //id = cursor.getInt(0); //first col is id (int)
                desc = cursor.getString(1);
                items.add(desc);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        //database.close();
        return items;
    }
}
