package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Shopping_cart extends AppCompatActivity {

    private ListView lvfoods;
    private Button send;
    private Button sendOut;
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
        sendOut = findViewById(R.id.Shopping_btn_send);

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
                } else if (view.getId() == R.id.Shopping_btn_send){

                    AlertDialog.Builder builder = new AlertDialog.Builder(Shopping_cart.this);
                    builder.setCancelable(false);
                    builder.setTitle("確認餐點內容");
                    builder.setMessage("請問您確認您的餐點內容皆正確無誤嗎?送出後無法修改!");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(Shopping_cart.this, "餐點訂單送出~請耐心等候~", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Shopping_cart.this, MainActivity.class);
                            intent.setClass(Shopping_cart.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(Shopping_cart.this, "已確認送出", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();
                }
            }
        };
        send.setOnClickListener(listener);
        date.setOnClickListener(listener);
        time.setOnClickListener(listener);
        sendOut.setOnClickListener(listener);

        int total_price = 0;
        for (food_item item : food_list_choose) {
            total_price += item.getFood_num() * (item.getFood_price()+ item.getFood_price_extra());
        }
        total.setText("$" + String.valueOf(total_price));


        cart_list_view_adapter adapter = new cart_list_view_adapter(this, food_list_choose);
        lvfoods.setAdapter(adapter);

        ViewGroup.LayoutParams params = lvfoods.getLayoutParams();
        params.height = 550 * food_list_choose.size(); // 设置高度为200dp，你可以根据需要进行修改
        lvfoods.setLayoutParams(params);
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