package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class order extends AppCompatActivity {
    private Button btn;
    private Button add;
    private Button minus;
    private food_item food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btn = findViewById(R.id.button);
        add = findViewById(R.id.button2);
        minus = findViewById(R.id.button3);
        TextView tv_cname = findViewById(R.id.order_tv_cname);
        TextView tv_ename = findViewById(R.id.order_tv_ename);
        TextView tv_price = findViewById(R.id.order_tv_price);
        TextView tv_num = findViewById(R.id.order_tv_food_num);
        ImageView iv_image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        List<food_item> food_list = intent.getParcelableArrayListExtra("foodList");
        List<food_item> food_list_choose = intent.getParcelableArrayListExtra("foodListChoose");
        int position = intent.getIntExtra("position", 0);
        int from = intent.getIntExtra("from", 1);
        if (from == 1) {
            food = food_list.get(position);
        } else if (from == 2) {
            food = food_list_choose.get(position);
        }

        tv_cname.setText(food.getFood_cname());
        tv_ename.setText(food.getFood_ename());
        tv_price.setText("$"+String.valueOf(food.getFood_price()));
        tv_num.setText(String.valueOf(food.getFood_num()));
        iv_image.setImageResource(food.getImage_id());
        iv_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button) {
                    boolean cnameExists = false;
                    for (food_item item : food_list_choose) {
                        if (item.getFood_cname().equals(food.getFood_cname())) {
                            item.setFood_num(food.getFood_num());
                            cnameExists = true;
                            break;
                        }
                    }
                    if (food.getFood_num() != 0 && cnameExists == false) {
                        food_list_choose.add(food);
                    } else if (food.getFood_num() == 0 && cnameExists) {
                        food_list_choose.remove(position);
                    }
                    Intent intent = new Intent();
                    if (from == 1) {
                        intent = new Intent(order.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                        bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                        intent.putExtras(bundle);
                        intent.setClass(order.this, MainActivity.class);
                    } else if (from == 2) {
                        intent = new Intent(order.this, Shopping_cart.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                        bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                        intent.putExtras(bundle);
                        intent.setClass(order.this, Shopping_cart.class);
                    }
                    startActivity(intent);
                } else if (view.getId() == R.id.button2 && food.getFood_num() >= 1) {
                    food.setFood_num(food.getFood_num() - 1);
                    tv_num.setText(String.valueOf(food.getFood_num()));
                } else if (view.getId() == R.id.button3) {
                    food.setFood_num(food.getFood_num() + 1);
                    tv_num.setText(String.valueOf(food.getFood_num()));
                }
            }
        };
        btn.setOnClickListener(listener);
        add.setOnClickListener(listener);
        minus.setOnClickListener(listener);
    }
}