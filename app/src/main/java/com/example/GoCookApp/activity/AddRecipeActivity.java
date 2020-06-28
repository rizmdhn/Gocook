package com.example.GoCookApp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.example.GoCookApp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class AddRecipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName, mPrepTime, mCookTime, mServings;
    private ImageView mImageView;
    String Categories;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        mButtonChooseImage = findViewById(R.id.buttonchoose);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mButtonUpload = findViewById(R.id.nextbtninfoid);
        mEditTextFileName = findViewById(R.id.recipetitleid);
        mPrepTime = findViewById(R.id.preptimeid);
        mCookTime = findViewById(R.id.cooktimeid);
        mServings = findViewById(R.id.servingsid);
        mImageView = findViewById(R.id.image_view);
        mStorageRef = FirebaseStorage.getInstance().getReference("foodimg"+ UUID.randomUUID());
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cobadeh");
        String regexmax = ".{30,}";
        awesomeValidation.addValidation(this, R.id.recipetitleid, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.titleerror);
        awesomeValidation.addValidation(this, R.id.recipetitleid, RegexTemplate.NOT_EMPTY, R.string.titleerror);

        awesomeValidation.addValidation(this, R.id.preptimeid, RegexTemplate.NOT_EMPTY, R.string.prepterror);

        awesomeValidation.addValidation(this, R.id.cooktimeid, RegexTemplate.NOT_EMPTY, R.string.cookterror);
        awesomeValidation.addValidation(this, R.id.servingsid, RegexTemplate.NOT_EMPTY, R.string.servingserror);
        awesomeValidation.addValidation(this, R.id.image_view, RegexTemplate.NOT_EMPTY, R.string.imageerror);
        awesomeValidation.addValidation(this, R.id.spinner1, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("")) {
                    return false;
                } else {
                    return true;
                }
            }
        }, validationHolder -> {
            TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
            textViewError.setError(validationHolder.getErrMsg());
            textViewError.setTextColor(Color.RED);
        }, validationHolder -> {
            TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
            textViewError.setError(null);
            textViewError.setTextColor(Color.BLACK);
        }, R.string.cateserror);



        mButtonChooseImage.setOnClickListener(v -> openFileChooser());
        mButtonUpload.setOnClickListener(v -> {
            if (mUploadTask != null && mUploadTask.isInProgress()) {
                Toast.makeText(AddRecipeActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
            } else {
                awesomeValidation.validate();
                uploadFile();
            }
        });


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Categories = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private void uploadFile() {
        if (mImageUri != null) {
            mStorageRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        Toast.makeText(AddRecipeActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                    return mStorageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                       Uri downloadUri = task.getResult();
                        Log.e("TAG", "then: " + downloadUri.toString());
                        if (downloadUri.toString().equals("")) {

                            Toast.makeText(AddRecipeActivity.this, "please insert an image!", Toast.LENGTH_SHORT).show();

                        }else{
                            Intent intent = new Intent(AddRecipeActivity.this, AddRecipeIngreActivity.class);
                            intent.putExtra("titlepass", mEditTextFileName.getText().toString());
                            intent.putExtra("imagepass", downloadUri.toString());
                            intent.putExtra("catepass", Categories);
                            intent.putExtra("servpass", mServings.getText().toString());
                            intent.putExtra("preppass", mPrepTime.getText().toString());
                            intent.putExtra("cookpass", mCookTime.getText().toString());
                            startActivity(intent);
                        }

//                        GeneralInfoREPO upload = new GeneralInfoREPO(mEditTextFileName.getText().toString().trim(),
//                                downloadUri.toString(),Categories,mPrepTime.getText().toString(),mCookTime.getText().toString(),mServings.getText().toString());
//                        mDatabaseRef.push().setValue(upload);
                    } else {
                        Toast.makeText(AddRecipeActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(AddRecipeActivity.this, "Please insert a Photo", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
