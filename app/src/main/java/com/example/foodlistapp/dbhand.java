package com.example.foodlistapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class dbhand {

    private static final String DATABASE_NAME = "order_data.db";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS menu (" +
            "date TEXT NOT NULL," +
            "time TEXT NOT NULL," +
            "count TEXT NOT NULL," +
            "name TEXT NOT NULL," +
            "total TEXT NOT NULL," +
            "img TEXT NOT NULL," +
            "id int NOT NULL" +
            ")";
    private AppCompatActivity activity;
    private SQLiteDatabase db;

    public dbhand(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open() {
        db = activity.openOrCreateDatabase(DATABASE_NAME, 0, null);
        db.execSQL(CREATE_TABLE);
    }

    public void addItem(String date, String time, String count, String name, String total, String img, int id) {
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("time", time);
        values.put("count", count);
        values.put("name", name);
        values.put("total", total);
        values.put("img", img);
        values.put("id", id);
        db.insert("menu", null, values);
    }

    public Cursor getAllItems() {
        return db.query("menu", null, null, null, null, null, null);
    }

    public int getItemCount() {
        Cursor cursor = db.rawQuery("SELECT id FROM menu ORDER BY id DESC", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int all() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM menu", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }
}
