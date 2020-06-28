package com.example.GoCookApp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.GeneralInfoREPO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddRecipeIngreActivity extends AppCompatActivity {
    public EditText i2;
    public EditText i3;
    public EditText i4;
    public EditText i5;
    public EditText i6;
    public EditText i7;
    public EditText i8;
    public EditText i9;
    public EditText i10;
    public EditText i11;
    public EditText i12;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipeingre);
        String titlevalue = getIntent().getStringExtra("titlepass");
        String imgvalue = getIntent().getStringExtra("imagepass");
        String catevalue = getIntent().getStringExtra("catepass");
        String prepvalue = getIntent().getStringExtra("preppass");
        String cookvalue = getIntent().getStringExtra("cookpass");
        String servvalue = getIntent().getStringExtra("servpass");
        EditText i1 = (EditText) findViewById(R.id.ing1id);
        i2 = findViewById(R.id.ing2id);
        i3 = findViewById(R.id.ing3id);
        i4 = findViewById(R.id.ing4id);
        i5 = findViewById(R.id.ing5id);
        i6 = findViewById(R.id.ing6id);
        i7 = findViewById(R.id.ing7id);
        i8 = findViewById(R.id.ing8id);
        i9 = findViewById(R.id.ing9id);
        i10 = findViewById(R.id.ing10id);
        i11 = findViewById(R.id.ing11id);
        i12 = findViewById(R.id.ing12id);

        final ArrayList geninfo = new ArrayList();
        geninfo.add(titlevalue);
        geninfo.add(imgvalue);
        geninfo.add(catevalue);
        geninfo.add(prepvalue);
        geninfo.add(cookvalue);
        geninfo.add(servvalue);
        Log.i("ingredients","this is after intent get value");


        final String text = i1.getText().toString();



        final ArrayList inginfo = new ArrayList();
        inginfo.add(i1.getText().toString());
        inginfo.add(i2.getText().toString());
        inginfo.add(i3.getText().toString());
        inginfo.add(i4.getText().toString());
        inginfo.add(i5.getText().toString());
        inginfo.add(i6.getText().toString());
        inginfo.add(i7.getText().toString());
        inginfo.add(i8.getText().toString());
        inginfo.add(i9.getText().toString());
        inginfo.add(i10.getText().toString());
        inginfo.add(i11.getText().toString());
        inginfo.add(i12.getText().toString());
//        final ArrayList ingfinalinfo = new ArrayList();
//        for (int x =0; x<12;x++){
//            if(inginfo.get(x).toString() != ""){
//                ingfinalinfo.add(inginfo.get(x).toString());
//            }
//            else {
//
//            }
//        }
        Button btn = findViewById(R.id.nextbtningid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test",text);
                if(i1.getText().toString().equals("")){
                    Toast.makeText(AddRecipeIngreActivity.this, R.string.ingreserror, Toast.LENGTH_SHORT).show();
            }else{
                    final ArrayList inginfo = new ArrayList();
                    inginfo.add(i1.getText().toString());
                    inginfo.add(i2.getText().toString());
                    inginfo.add(i3.getText().toString());
                    inginfo.add(i4.getText().toString());
                    inginfo.add(i5.getText().toString());
                    inginfo.add(i6.getText().toString());
                    inginfo.add(i7.getText().toString());
                    inginfo.add(i8.getText().toString());
                    inginfo.add(i9.getText().toString());
                    inginfo.add(i10.getText().toString());
                    inginfo.add(i11.getText().toString());
                    inginfo.add(i12.getText().toString());
                    Intent intentbos = new Intent(AddRecipeIngreActivity.this,AddRecipeStepActivity.class);
                    intentbos.putExtra("generalinfo", geninfo);
                    intentbos.putExtra("ingredients", inginfo);

                    startActivity(intentbos);
                    Log.i("inginfo", inginfo.get(1).toString());

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
