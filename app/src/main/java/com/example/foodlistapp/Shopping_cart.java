package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Shopping_cart extends AppCompatActivity {

    private ListView lvfoods;
    private Button send;
    private TextView total;
    private TextView date;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        lvfoods = findViewById(R.id.Shopping_lv_chosen);
        send = findViewById(R.id.Shopping_btn_back);
        total = findViewById(R.id.Shopping_tv_total);
        date = findViewById(R.id.Shopping_tv_date);
        time = findViewById(R.id.Shopping_tv_time);

        Intent intent = getIntent();
        List<food_item> food_list = intent.getParcelableArrayListExtra("foodList");
        List<food_item> food_list_choose = intent.getParcelableArrayListExtra("foodListChoose");

        lvfoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Shopping_cart.this, order.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                intent.putExtras(bundle);
                intent.putExtra("position", i);
                intent.putExtra("from", 2);
                intent.setClass(Shopping_cart.this, order.class);
                startActivity(intent);
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.Shopping_btn_back) {
                    Intent intent = new Intent(Shopping_cart.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                    bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                    intent.putExtras(bundle);
                    intent.setClass(Shopping_cart.this, MainActivity.class);
                    startActivity(intent);
                } else if (view.getId() == R.id.Shopping_tv_date) {
                    showDatePickerDialog();
                } else if (view.getId() == R.id.Shopping_tv_time) {
                    showTimePickerDialog();
                }
            }
        };
        send.setOnClickListener(listener);
        date.setOnClickListener(listener);
        time.setOnClickListener(listener);

        int total_price = 0;
        for (food_item item : food_list_choose) {
            total_price += item.getFood_num() * item.getFood_price();
        }
        total.setText("$" + String.valueOf(total_price));

        cart_list_view_adapter adapter = new cart_list_view_adapter(this, food_list_choose);
        lvfoods.setAdapter(adapter);
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update text view with selected date
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        date.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Shopping_cart.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}