package com.example.foodlistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class cart_list_view_adapter extends BaseAdapter {

    private Context context;
    private List<food_item> food_list;

    public cart_list_view_adapter(Context context, List<food_item> food_list) {
        this.context = context;
        this.food_list = food_list;
    }

    @Override
    public int getCount() {
        return food_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cart_food_item_layout, viewGroup, false);
        }
        food_item food = food_list.get(i);
        ImageView iv = view.findViewById(R.id.imageView3);
        iv.setImageResource(food.getImage_id());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView food_cname_tv = view.findViewById(R.id.cart_food_cname_tv);
        food_cname_tv.setText(food.getFood_cname());
        if(food.getFood_price_extra() == 0){
            food_cname_tv.setText(food.getFood_cname());
        }else if(food.getFood_price_extra() == 15){
            food_cname_tv.setText(food.getFood_cname() + "(薯餅套餐)");
        }else if(food.getFood_price_extra() == 20){
            food_cname_tv.setText(food.getFood_cname() + "(麥克雞塊套餐)");
        }else if(food.getFood_price_extra() == 30){
            food_cname_tv.setText(food.getFood_cname() + "(沙拉套餐)");
        }


        TextView food_price_tv = view.findViewById(R.id.cart_food_price_tv);
        food_price_tv.setText("$" + String.valueOf(food.getFood_price() + food.getFood_price_extra()));

        TextView food_num_tv = view.findViewById(R.id.cart_food_num_tv);
        food_num_tv.setText("數量：" + String.valueOf(food.getFood_num()));

        return view;
    }
}

