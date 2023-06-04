package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class record extends AppCompatActivity {

    private ListView lv;
    private dbhand databaseHandler;
    private record_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = findViewById(R.id.record_lv);
        databaseHandler = new dbhand(this);
        adapter = new record_adapter(this, databaseHandler);
        lv.setAdapter(adapter);
    }
}