package com.example.foodlistapp;

import android.annotation.SuppressLint;
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

    private dbhand databaseHandler;

    public record_adapter(Context context, dbhand databaseHandler) {
        this.context = context;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public int getCount() {
        databaseHandler.open();
        return databaseHandler.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("Range")
    @Override
    public View getView(int position, View view, ViewGroup viewgroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.record_item, viewgroup, false);
        }
        databaseHandler.open();
        Cursor cursor = databaseHandler.getAllItems();
        if (cursor != null && cursor.moveToPosition(position)) {
            TextView date = view.findViewById(R.id.textView20);
            date.setText(cursor.getString(cursor.getColumnIndex("date")));
            TextView time = view.findViewById(R.id.textView21);
            time.setText(cursor.getString(cursor.getColumnIndex("id")));

            ListView lv = view.findViewById(R.id.record_lv);
            List<food_item> food_list = new ArrayList<>();
            int total_price = 0;
            int counter = 0;
            do {
                counter++;
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String count = cursor.getString(cursor.getColumnIndex("count"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("total"));
                total_price += Integer.parseInt(price)*Integer.parseInt(count);
                String img = cursor.getString(cursor.getColumnIndex("img"));
                food_list.add(new food_item(Integer.parseInt(img), Integer.parseInt(price), name, "0", Integer.parseInt(count)));

                if (!cursor.moveToNext() || id != cursor.getInt(cursor.getColumnIndex("id"))) {
                    break;
                }
            } while (true);

            cart_list_view_adapter adapter = new cart_list_view_adapter(context, food_list);
            lv.setAdapter(adapter);

            ViewGroup.LayoutParams params = lv.getLayoutParams();
            params.height = 500 * counter; // 根据需要进行修改
            lv.setLayoutParams(params);

            TextView total = view.findViewById(R.id.textView26);
            total.setText("$" + String.valueOf(total_price));
        }
        cursor.close();

        return view;
    }

}
