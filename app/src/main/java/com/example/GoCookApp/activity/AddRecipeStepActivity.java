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

import com.example.GoCookApp.R;
import com.example.GoCookApp.REPO.GeneralInfoREPO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddRecipeStepActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private EditText s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipestep);
        Intent intent = getIntent();
        final ArrayList<String> generalinfopage1 = (ArrayList) intent.getSerializableExtra("generalinfo");
        final ArrayList<String> inggpage2  = (ArrayList) intent.getSerializableExtra("ingredients");
        s1 = findViewById(R.id.step1id);
        s2 = findViewById(R.id.step2id);
        s3 = findViewById(R.id.step3id);
        s4 = findViewById(R.id.step4id);
        s5 = findViewById(R.id.step5id);
        s6 = findViewById(R.id.step6id);
        s7 = findViewById(R.id.step7id);
        s8 = findViewById(R.id.step8id);
        s9 = findViewById(R.id.step9id);
        s10 = findViewById(R.id.step10id);
        s11 = findViewById(R.id.step11id);
        s12 = findViewById(R.id.step12id);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Recipe");

        Button btnsubmit = findViewById(R.id.btnsumbitid);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1.getText().toString().equals("")){
                    Toast.makeText(AddRecipeStepActivity.this, R.string.stepserror, Toast.LENGTH_SHORT).show();
                }else{
                final ArrayList stepinfo = new ArrayList();
                stepinfo.add(s1.getText().toString());
                stepinfo.add(s2.getText().toString());
                stepinfo.add(s3.getText().toString());
                stepinfo.add(s4.getText().toString());
                stepinfo.add(s5.getText().toString());
                stepinfo.add(s6.getText().toString());
                stepinfo.add(s7.getText().toString());
                stepinfo.add(s8.getText().toString());
                stepinfo.add(s9.getText().toString());
                stepinfo.add(s10.getText().toString());
                stepinfo.add(s11.getText().toString());
                stepinfo.add(s12.getText().toString());
                Log.i("stepbystep",stepinfo.get(0).toString());
                GeneralInfoREPO upload = new GeneralInfoREPO(generalinfopage1.get(0).toString(),generalinfopage1.get(1).toString(),
                        generalinfopage1.get(2).toString(),generalinfopage1.get(3).toString(),generalinfopage1.get(4).toString(),
                        inggpage2,stepinfo,generalinfopage1.get(5).toString());
                mDatabaseRef.push().setValue(upload);
                Toast.makeText(AddRecipeStepActivity.this, "Upload Success" , Toast.LENGTH_SHORT).show();
                Intent home = new Intent(AddRecipeStepActivity.this, MasterActivity.class);
                startActivity(home);

            }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
