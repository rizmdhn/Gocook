package com.example.GoCookApp.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.makeramen.roundedimageview.RoundedImageView;
import com.example.GoCookApp.R;
import com.example.GoCookApp.activity.menulist.BreakfastMenuActivity;
import com.example.GoCookApp.activity.menulist.DinnerMenuActivity;
import com.example.GoCookApp.activity.menulist.LunchMenuActivity;
import com.example.GoCookApp.activity.menulist.SnackMenuActivity;

public class CategoriesFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.categories, container, false);
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoundedImageView breakp = view.findViewById(R.id.image1);
        RoundedImageView lunchp = view.findViewById(R.id.image2);
        RoundedImageView dinnerp = view.findViewById(R.id.image3);
        RoundedImageView snackp = view.findViewById(R.id.image4);
        breakp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BreakfastMenuActivity.class));
            }
        });
        lunchp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LunchMenuActivity.class));
            }
        });
        dinnerp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DinnerMenuActivity.class));
            }
        });
        snackp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SnackMenuActivity.class));
            }
        });
}
}
