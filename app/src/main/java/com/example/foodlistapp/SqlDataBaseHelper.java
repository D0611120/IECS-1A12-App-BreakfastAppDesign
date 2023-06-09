package com.example.foodlistapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class SqlDataBaseHelper {

    private static final String DATABASE_NAME = "order_data.db";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS menu (" +
            "date DATE NOT NULL," +
            "time TIME NOT NULL," +
            "count TEXT NOT NULL," +
            "name TEXT NOT NULL," +
            "total TEXT NOT NULL," +
            "img TEXT NOT NULL" +
            ")";
    private AppCompatActivity activity;
    private SQLiteDatabase db;

    public SqlDataBaseHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public boolean open() {
        db = activity.openOrCreateDatabase(DATABASE_NAME, 0, null);
        if (db != null) {
            db.execSQL(CREATE_TABLE);
        } else {
            return false;
        }
        return true;
    }


    public void insert() {
        db = activity.openOrCreateDatabase(DATABASE_NAME,0,null);
        db.execSQL("INSERT INTO menu (date) VALUES ('123')");
    }

    public void addItem(String date, String time, String count, String name, String total, String img) {
        ContentValues values = new ContentValues();
        db = activity.openOrCreateDatabase(DATABASE_NAME,0,null);
        values.put("date", date);
        values.put("time", time);
        values.put("count", count);
        values.put("name", name);
        values.put("total", total);
        values.put("img", img);
        db.insert("menu", null, values);
    }

    public Cursor getAll() {
        Cursor cursor = db.rawQuery("SELECT * FROM menu", null);
        return cursor;
    }


}

