package com.example.foodlistapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class record_adapter extends BaseAdapter {

    private Context context;

    private SqlDataBaseHelper databaseHandler;

    public record_adapter(Context context, SqlDataBaseHelper databaseHandler) {
        this.context = context;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public int getCount() {
        Cursor cursor = databaseHandler.getAll();
        int i = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                i++;
            } while (cursor.moveToNext());
        }
        return i;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.record_item, viewgroup, false);
        }

        Cursor cursor = databaseHandler.getAll();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                TextView date = view.findViewById(R.id.textView20);
                date.setText(cursor.getString(cursor.getColumnIndex("date")));
                TextView time = view.findViewById(R.id.textView21);
                time.setText(cursor.getString(cursor.getColumnIndex("time")));

                String count = cursor.getString(cursor.getColumnIndex("count"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("total"));
                String img = cursor.getString(cursor.getColumnIndex("img"));

                ListView lv = view.findViewById(R.id.record_lv);
                List<food_item> food_list = new ArrayList<>();
                food_list.add(new food_item(Integer.parseInt(img), Integer.parseInt(price), name, "0", Integer.parseInt(count)));
                cart_list_view_adapter adapter = new cart_list_view_adapter(context, food_list);
                lv.setAdapter(adapter);

                TextView total = view.findViewById(R.id.textView26);
                total.setText("9999");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
}
