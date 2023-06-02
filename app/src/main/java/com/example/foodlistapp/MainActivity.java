package com.example.foodlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvfoods;
    private List<food_item> food_list;
    private List<food_item> food_list_choose;
    private FloatingActionButton btn_nextpage;
    private TextView information;
    private TextView news;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvfoods = findViewById(R.id.lv1);
        btn_nextpage = findViewById(R.id.fab_next);
        information = findViewById(R.id.main_tv_information);
        news = findViewById(R.id.main_tv_news);

        information.setPaintFlags(information.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        news.setPaintFlags(news.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        food_list = new ArrayList<>();
        food_list_choose = new ArrayList<>();
        food_list.add(new food_item(R.drawable.burger1, 998, "鱈魚龍蝦沙拉漢堡", "Codfish & Lobster Salad Burger", 0));
        food_list.add(new food_item(R.drawable.burger2, 998, "起司鱈魚芝加哥堡", "Codfish & Cheese Mr. Burger", 0));
        food_list.add(new food_item(R.drawable.burger3, 998, "爆料炸蝦厚牛漢堡", "Beef & Shrimp Burger", 0));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.fab_next) {
                    Intent intent = new Intent(MainActivity.this, Shopping_cart.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                    bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, Shopping_cart.class);
                    startActivity(intent);
                } else if (view.getId() == R.id.main_tv_information) {
                    Intent intent = new Intent(MainActivity.this, imformation.class);
                    startActivity(intent);
                } else if (view.getId() == R.id.main_tv_news) {
                    Intent intent = new Intent(MainActivity.this, news.class);
                    startActivity(intent);
                }
            }
        };

        news.setOnClickListener(listener);
        information.setOnClickListener(listener);
        btn_nextpage.setOnClickListener(listener);

        lvfoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, order.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                intent.putExtras(bundle);
                intent.putExtra("position", i);
                intent.putExtra("from", 1);
                intent.setClass(MainActivity.this, order.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent.getParcelableArrayListExtra("foodList") != null)
            food_list = intent.getParcelableArrayListExtra("foodList");
        if (intent.getParcelableArrayListExtra("foodListChoose") != null)
            food_list_choose = intent.getParcelableArrayListExtra("foodListChoose");


        list_view_adapter adapter = new list_view_adapter(this, food_list);
        lvfoods.setAdapter(adapter);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                int id = item.getItemId();

                if (id == R.id.action_home) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_shoppingcart) {
                    Intent intent = new Intent(MainActivity.this, Shopping_cart.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                    bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, Shopping_cart.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_history) {
                    Intent intent = new Intent(MainActivity.this, record.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_information) {
                    Intent intent = new Intent(MainActivity.this, imformation.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_news) {
                    Intent intent = new Intent(MainActivity.this, news.class);
                    startActivity(intent);
                }

                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // 依照id判斷點了哪個項目並做相應事件
        if (id == R.id.toolbar_home) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_shoppingcart) {
            Intent intent = new Intent(MainActivity.this, Shopping_cart.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
            bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
            intent.putExtras(bundle);
            intent.setClass(MainActivity.this, Shopping_cart.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_history) {
            Intent intent = new Intent(MainActivity.this, record.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_information) {
            Intent intent = new Intent(MainActivity.this, imformation.class);
            startActivity(intent);
        }else if (id == R.id.toolbar_news) {
            Intent intent = new Intent(MainActivity.this, news.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}