package com.example.GoCookApp.activity.menulist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.GoCookApp.R;
import com.example.GoCookApp.activity.MasterActivity;
import com.example.GoCookApp.fragment.DessertFragment;

public class DinnerMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadFragment(new DessertFragment());
        ImageView img = findViewById(R.id.backbutton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DinnerMenuActivity.this, MasterActivity.class));
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
}
