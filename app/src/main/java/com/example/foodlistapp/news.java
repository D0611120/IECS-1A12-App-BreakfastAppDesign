package com.example.foodlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class news extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ListView lvfoods;
    private List<food_item> food_list;
    private List<food_item> food_list_choose;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);

        food_list = new ArrayList<>();
        food_list_choose = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        // 用toolbar做為APP的ActionBar
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                int id = item.getItemId();

                if (id == R.id.action_home) {
                    Intent intent = new Intent(news.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_shoppingcart) {
                    Intent intent = new Intent(news.this, Shopping_cart.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
                    bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
                    intent.putExtras(bundle);
                    intent.setClass(news.this, Shopping_cart.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_history) {
                    Intent intent = new Intent(news.this, record.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_information) {
                    Intent intent = new Intent(news.this, imformation.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_news) {
                    Intent intent = new Intent(news.this, news.class);
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
            Intent intent = new Intent(news.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_shoppingcart) {
            Intent intent = new Intent(news.this, Shopping_cart.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("foodList", new ArrayList<>(food_list));
            bundle.putParcelableArrayList("foodListChoose", new ArrayList<>(food_list_choose));
            intent.putExtras(bundle);
            intent.setClass(news.this, Shopping_cart.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_history) {
            Intent intent = new Intent(news.this, record.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_information) {
            Intent intent = new Intent(news.this, imformation.class);
            startActivity(intent);
        }else if (id == R.id.toolbar_news) {
            Intent intent = new Intent(news.this, news.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}