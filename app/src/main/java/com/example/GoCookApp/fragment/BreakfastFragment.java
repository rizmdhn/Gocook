package com.example.GoCookApp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.MenuREPO;
import com.example.GoCookApp.adapter.ScrambleRecyclerviewadapter;

import java.util.ArrayList;

public class BreakfastFragment extends Fragment {

    int[] image = {R.drawable.scrambledegg,R.drawable.pancake,R.drawable.waffle,R.drawable.sandwich};
    String[] title = {"Scrambeled Egg","Pancake","Waffle","Sandwich"};
    String[] price = {"+ - 30 Min","+ - 35 Min","+ - 40 Min","+ - 35 Min"};
    ArrayList<MenuREPO> arrayList;
    private RecyclerView trip_recyclerview;
    private ScrambleRecyclerviewadapter recyclerviewadapter;

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
        recyclerviewadapter = new ScrambleRecyclerviewadapter(getContext(), arrayList);
        trip_recyclerview.setAdapter(recyclerviewadapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
}
