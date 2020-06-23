package com.example.GoCookApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.HomeREPO;
import com.example.GoCookApp.activity.Steakactivity;

import java.util.ArrayList;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<HomeREPO> list;

    public HomeRecyclerAdapter(Context context, ArrayList<HomeREPO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_homerecyclerview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        HomeREPO homeREPO = list.get(i);

        myViewHolder.imageView.setImageResource(homeREPO.getImages());
        myViewHolder.title.setText(homeREPO.getTitles());
        myViewHolder.categories.setText(homeREPO.getCat());
        myViewHolder.cookt.setText(homeREPO.getCookt());
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, Steakactivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, categories, cookt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlef);
            categories = itemView.findViewById(R.id.categories);
            imageView = itemView.findViewById(R.id.imageview);
            cookt = itemView.findViewById(R.id.cooktime);
        }
    }


}
