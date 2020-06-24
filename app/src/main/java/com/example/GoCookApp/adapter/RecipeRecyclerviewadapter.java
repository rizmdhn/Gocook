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
import com.example.GoCookApp.REPO.RecipeREPO;
import com.example.GoCookApp.activity.Steakactivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


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
import com.example.GoCookApp.REPO.RecipeREPO;
import com.example.GoCookApp.activity.Steakactivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class RecipeRecyclerviewadapter extends RecyclerView.Adapter<RecipeRecyclerviewadapter.ViewHolder> {

        Context context;
        ArrayList<RecipeREPO> list;
        LinearLayout linearLayout;

        public RecipeRecyclerviewadapter(Context context, ArrayList<RecipeREPO> list) {
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

            RecipeREPO recipeREPO = list.get(i);

            Picasso.with(context).load(recipeREPO.getImage()).fit().centerCrop().into(viewHolder.menuImage);
            viewHolder.menu_title.setText(recipeREPO.getTitle());
            viewHolder.menu_categories.setText(recipeREPO.getCategories());
            viewHolder.menu_cooktime.setText(recipeREPO.getCookt());
            viewHolder.menuImage.setOnClickListener(new View.OnClickListener() {
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

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView menuImage;
            TextView menu_title, menu_categories, menu_cooktime;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                menuImage = itemView.findViewById(R.id.imageviewmenu);
                menu_title = itemView.findViewById(R.id.titlemenu);
                menu_categories = itemView.findViewById(R.id.categoriesmenu);
                menu_cooktime = itemView.findViewById(R.id.cooktmenu);
            }
        }
    }


