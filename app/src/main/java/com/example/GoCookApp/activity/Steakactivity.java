package com.example.GoCookApp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.HomeREPO;
import com.example.GoCookApp.REPO.RecipeREPO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Steakactivity extends AppCompatActivity {
    ArrayList<RecipeREPO> arrayList;
    Context context;
    String title = "Nasi Goreng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steak);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            title = extras.getString("title");
            Log.i("cek", title);
        }else{
            Log.i("cek2", extras.getString("title"));
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query myRef = database.getReference("Recipe").orderByChild("title").equalTo(title);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String image1 = ds.child("image").getValue(String.class);
                    String title = ds.child("title").getValue(String.class);
                    String cat = ds.child("categories").getValue(String.class);
                    String cook = ds.child("cooktime").getValue(String.class);
                    ArrayList ingredient = new ArrayList<>();
                    ArrayList howto = new ArrayList<>();
                    for (DataSnapshot ck : ds.child("ingredients").getChildren()) {
                        ingredient.add(ck.getValue(String.class));
                    }
                    for (DataSnapshot ck : ds.child("howtomake").getChildren()) {
                        howto.add(ck.getValue(String.class));
                    }
                    ImageView menuImage = findViewById(R.id.imageviewmenu);
                    TextView menu_title = findViewById(R.id.titlemenu);
                    TextView menu_categories = findViewById(R.id.categoriesmenu);
                    TextView menu_cooktime = findViewById(R.id.cooktmenu);
                    TextView menu_ingridients = findViewById(R.id.ingdredient_menu);
                    TextView menu_howto = findViewById(R.id.howto_menu);
                    Picasso.with(context).load(image1).fit().centerCrop().into(menuImage);
                    menu_title.setText(title);
                    menu_categories.setText(cat);
                    menu_cooktime.setText(cook);
                    for (int i = 0; i < ingredient.size(); i++) {
                        if(ingredient.get(i).toString().equals("")){

                        }else{
                            menu_ingridients.append(String.valueOf(i + 1) + ".  ");
                            menu_ingridients.append(ingredient.get(i).toString());
                            menu_ingridients.append("\n \n");
                        }
                    }
                    for (int i = 0; i < howto.size(); i++) {
                        if(howto.get(i).toString().equals("")){

                    }else{
                            menu_howto.append(String.valueOf(i + 1) + ".  ");
                            menu_howto.append(howto.get(i).toString());
                            menu_howto.append("\n \n");}
                        }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ImageView img = findViewById(R.id.backbutton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
