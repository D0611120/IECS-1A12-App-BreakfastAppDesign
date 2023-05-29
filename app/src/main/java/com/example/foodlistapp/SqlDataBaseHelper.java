package com.example.foodlistapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;

public class SqlDataBaseHelper extends SQLiteOpenHelper {

    private static final String DataBaseName = "order_data";
    private static final int DataBaseVersion = 1;


    public SqlDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,String TableName) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SqlTable = "CREATE TABLE IF NOT EXISTS menu (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "count text not null," +
                "name text not null," +
                "total TEXT not null" +
                ")";

        db.execSQL(SqlTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL = "DROP TABLE menu";
        db.execSQL(SQL);
    }


}
