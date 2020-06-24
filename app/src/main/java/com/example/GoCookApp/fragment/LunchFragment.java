package com.example.GoCookApp.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.example.GoCookApp.adapter.NachosRecyclerviewadapter;
import com.example.GoCookApp.adapter.SteakRecyclerviewadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LunchFragment extends Fragment {

    ArrayList<MenuREPO> arrayList;
    private RecyclerView trip_recyclerview;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList myrecipedataimage = new ArrayList<>();
    private ArrayList myrecipedatatitle = new ArrayList<>();
    private ArrayList myrecipedatacooktime = new ArrayList<>();
    private SteakRecyclerviewadapter recyclerviewadapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        trip_recyclerview = view.findViewById(R.id.trip_recyclerview);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        trip_recyclerview.setLayoutManager(layoutManager1);
        trip_recyclerview.setItemAnimator(new DefaultItemAnimator());
        trip_recyclerview.setNestedScrollingEnabled(false);
        Log.i("LIATTDONG0", "ONCREATE");


        arrayList = new ArrayList<>();
        // this is firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Recipe");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("LIATTDONG", "ini udah masuk snapshot");
                for(DataSnapshot ds : snapshot.getChildren()){
                    String image = ds.child("image").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String cooktime = ds.child("cooktime").getValue(String.class);
                    myrecipedataimage.add(image);
                    myrecipedatatitle.add(title);
                    myrecipedatacooktime.add(cooktime);

                }
                for (int i = 0; i < myrecipedataimage.size(); i++) {
                    String recimg = myrecipedataimage.get(i).toString();

                    String rectit = myrecipedatatitle.get(i).toString();
                    String recct = myrecipedatacooktime.get(i).toString();
                    MenuREPO menuREPO = new MenuREPO(recimg,rectit,recct);
                    Log.i("test", rectit);
                    arrayList.add(menuREPO);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error Detected","Somehow its not working dude");
            }
        });





        recyclerviewadapter = new SteakRecyclerviewadapter(getContext(), arrayList);
        trip_recyclerview.setAdapter(recyclerviewadapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
}
