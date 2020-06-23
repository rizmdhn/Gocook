package com.example.GoCookApp.fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.MenuREPO;
import com.example.GoCookApp.adapter.ChickenRecyclerviewadapter;

import java.util.ArrayList;

public class DinnerFragment extends Fragment {

    int[] image = {R.drawable.roastedchic,R.drawable.salmon,R.drawable.noodle,R.drawable.ribss};
    String[] title = {"Roasted Chicken","Salmon Steak","Soba Noodle","BBQ Ribs"};
    String[] price = {"+ - 1-1.5 Hour","+ - 45 Min","+ - 35 Min","+ - 3 Hour"};
    ArrayList<MenuREPO> arrayList;
    private RecyclerView trip_recyclerview;
    private ChickenRecyclerviewadapter recyclerviewadapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        trip_recyclerview = view.findViewById(R.id.trip_recyclerview);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        trip_recyclerview.setLayoutManager(layoutManager1);
        trip_recyclerview.setItemAnimator(new DefaultItemAnimator());
        trip_recyclerview.setNestedScrollingEnabled(false);


        arrayList = new ArrayList<>();


        for (int i = 0; i < image.length; i++) {
            MenuREPO menuREPO = new MenuREPO(image[i],title[i],price[i]);
            arrayList.add(menuREPO);
        }
        recyclerviewadapter = new ChickenRecyclerviewadapter(getContext(), arrayList);
        trip_recyclerview.setAdapter(recyclerviewadapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
}
