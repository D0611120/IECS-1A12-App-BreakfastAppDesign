package com.example.foodlistapp;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "order_data";
    private static final int DATABASE_VERSION = 1;

    public SqlDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTable = "CREATE TABLE IF NOT EXISTS menu (" +
                "date DATE NOT NULL," +
                "time TIME NOT NULL," +
                "count TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "total TEXT NOT NULL" +
                ")";

        db.execSQL(sqlTable);
    }

    public List<Map<String, String>> getAllItems() {
        List<Map<String, String>> itemList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM menu", null);

        if (cursor.moveToFirst()) {
            do {
                Map<String, String> itemData = new HashMap<>();

                String date = cursor.getString(cursor.getColumnIndex("date"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String count = cursor.getString(cursor.getColumnIndex("count"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String total = cursor.getString(cursor.getColumnIndex("total"));

                itemData.put("date", date);
                itemData.put("time", time);
                itemData.put("count", count);
                itemData.put("name", name);
                itemData.put("total", total);

                itemList.add(itemData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemList;
    }


    public void addItem(String date, String time, String count, String name, String total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("time", time);
        values.put("count", count);
        values.put("name", name);
        values.put("total", total);
        db.insert("menu", null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String sql = "DROP TABLE IF EXISTS menu";
        db.execSQL(sql);
        onCreate(db);
    }
}

