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

        /*
        List<food_item> food_list = new ArrayList<>();
        food_list.add(new food_item(R.drawable.burger1, 998, "鱈魚龍蝦沙拉漢堡", "Codfish & Lobster Salad Burger", 0));
        food_list.add(new food_item(R.drawable.burger2, 998, "起司鱈魚芝加哥堡", "Codfish & Cheese Mr. Burger", 0));
        food_list.add(new food_item(R.drawable.burger3, 998, "爆料炸蝦厚牛漢堡", "Beef & Shrimp Burger", 0));
        cart_list_view_adapter adapter = new cart_list_view_adapter(this, food_list);
        */

        databaseHandler = new dbhand(this);
        adapter = new record_adapter(this, databaseHandler);
        lv.setAdapter(adapter);
    }
}