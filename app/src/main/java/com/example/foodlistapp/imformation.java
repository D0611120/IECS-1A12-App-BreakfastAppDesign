package com.example.foodlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class imformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);

        RelativeLayout r_layout = (RelativeLayout) findViewById(R.id.re_layout);
        r_layout.setOnClickListener((View.OnClickListener) this);

        TextView text_up = (TextView) findViewById(R.id.imformaion_text4);
        text_up.bringToFront();
        text_up.setOnClickListener((View.OnClickListener) this);
    }

}