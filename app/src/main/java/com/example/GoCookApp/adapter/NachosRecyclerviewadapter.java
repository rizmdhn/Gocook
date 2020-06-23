package com.example.GoCookApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.MenuREPO;
import com.example.GoCookApp.activity.NachoActivity;

import java.util.ArrayList;

public class NachosRecyclerviewadapter extends RecyclerView.Adapter<NachosRecyclerviewadapter.ViewHolder> {

    Context context;
    ArrayList<MenuREPO> list;
    LinearLayout linearLayout;

    public NachosRecyclerviewadapter(Context context, ArrayList<MenuREPO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        MenuREPO menuREPO = list.get(i);

        viewHolder.tripImage.setImageResource(menuREPO.getImage());
        viewHolder.trip_title.setText(menuREPO.getTitile());
        viewHolder.trip_price.setText(menuREPO.getPrice());
        viewHolder.tripImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, NachoActivity.class));
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView tripImage;
        TextView trip_title, trip_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tripImage = itemView.findViewById(R.id.tripImage);
            trip_title = itemView.findViewById(R.id.trip_title);
            trip_price = itemView.findViewById(R.id.trip_price);
        }
    }
}
