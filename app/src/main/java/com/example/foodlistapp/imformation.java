package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class imformation extends AppCompatActivity{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);



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
                    Intent intent = new Intent(imformation.this, MainActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_history) {
                    Intent intent = new Intent(imformation.this, record.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_information) {
                    Intent intent = new Intent(imformation.this, imformation.class);
                    startActivity(intent);
                }
                else if (id == R.id.action_news) {
                    Intent intent = new Intent(imformation.this, news.class);
                    startActivity(intent);
                }

                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // 依照id判斷點了哪個項目並做相應事件
        if (id == R.id.toolbar_home) {
            Intent intent = new Intent(imformation.this, MainActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.toolbar_history) {
            Intent intent = new Intent(imformation.this, record.class);
            startActivity(intent);
        }
        else if (id == R.id.toolbar_information) {
            Intent intent = new Intent(imformation.this, imformation.class);
            startActivity(intent);
        }else if (id == R.id.toolbar_news) {
            Intent intent = new Intent(imformation.this, news.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}





