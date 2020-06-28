package com.example.GoCookApp.activity.menulist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.GoCookApp.R;
import com.example.GoCookApp.activity.RoastChic;
import com.example.GoCookApp.activity.Scrambledeggs;
import com.example.GoCookApp.activity.Steakactivity;
import com.example.GoCookApp.fragment.DessertFragment;
import com.example.GoCookApp.fragment.viewall;

public class ViewallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadFragment(new viewall());
        ImageView img = findViewById(R.id.backbutton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_containermenu, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /* LinearLayout l1,l2,l3,l4;
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
            finish();
            }
        });
    }*/
}
