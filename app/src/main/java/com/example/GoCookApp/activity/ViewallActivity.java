package com.example.GoCookApp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.GoCookApp.R;

public class ViewallActivity extends AppCompatActivity {
    LinearLayout l1,l2,l3,l4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);
        l1 = findViewById(R.id.layout1);
        l2 = findViewById(R.id.layout2);
        l3 = findViewById(R.id.layout3);
        l4 = findViewById(R.id.layout4);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewallActivity.this, Scrambledeggs.class));
            }
        });l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewallActivity.this, Steakactivity.class));
            }
        });l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewallActivity.this, Steakactivity.class));
            }
        });l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewallActivity.this, RoastChic.class));
            }
        });
        ImageView img = findViewById(R.id.backbutton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewallActivity.this, MasterActivity.class));
            }
        });
    }
}
