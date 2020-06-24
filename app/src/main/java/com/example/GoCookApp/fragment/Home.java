package com.example.GoCookApp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.GoCookApp.REPO.HomeREPO;
import com.example.GoCookApp.activity.NachoActivity;
import com.example.GoCookApp.activity.Scrambledeggs;
import com.example.GoCookApp.activity.Steakactivity;
import com.example.GoCookApp.activity.ViewallActivity;
import com.example.GoCookApp.adapter.HomeRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class Home extends Fragment {



    ArrayList homeimage1 = new ArrayList<>();
    ArrayList Title = new ArrayList<>();
    ArrayList Cat = new ArrayList<>();
    ArrayList Cookt = new ArrayList<>();

    ArrayList<HomeREPO> arrayList;
    HomeRecyclerAdapter adapter;
    private RecyclerView recyclerview;
    private TextView search_layout;
    RoundedImageView egg,steak,snack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(layoutManager1);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setNestedScrollingEnabled(false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recipe");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot test : snapshot.getChildren()){
                    String image1 = test.child("image").getValue(String.class);
                    String title = test.child("title").getValue(String.class);
                    String cat = test.child("categories").getValue(String.class);
                    String cook = test.child("cooktime").getValue(String.class);

                    homeimage1.add(image1);
                    Title.add(title);
                    Cat.add(cat);
                    Cookt.add(cook);


                }

                arrayList = new ArrayList<>();

                for (int i = 0; i < 3; i++) {
                    HomeREPO homeREPO = new HomeREPO(homeimage1.get(i).toString(), Title.get(i).toString(), Cat.get(i).toString(),Cookt.get(i).toString());
                    arrayList.add(homeREPO);
                }

                adapter = new HomeRecyclerAdapter(getContext(), arrayList);
                recyclerview.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        egg = view.findViewById(R.id.eggpic);
        steak = view.findViewById(R.id.steakpic);
        snack = view.findViewById(R.id.snackpic);
        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Scrambledeggs.class));
            }
        });steak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Steakactivity.class));
            }
        });snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NachoActivity.class));
            }
        });
        TextView va = view.findViewById(R.id.viewall);
        va.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewallActivity.class));
            }
        });


    }
}
