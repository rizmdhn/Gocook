package com.example.GoCookApp.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.GoCookApp.REPO.GeneralInfoREPO;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.GoCookApp.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        mButtonChooseImage = findViewById(R.id.buttonchoose);
        mButtonUpload = findViewById(R.id.nextbtninfoid);
        mEditTextFileName = findViewById(R.id.recipetitleid);
        mPrepTime = findViewById(R.id.preptimeid);
        mCookTime = findViewById(R.id.cooktimeid);
        mServings = findViewById(R.id.servingsid);
        mImageView = findViewById(R.id.image_view);



        mStorageRef = FirebaseStorage.getInstance().getReference("foodimg"+ UUID.randomUUID());
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cobadeh");
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddRecipeActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
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

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            mStorageRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return mStorageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e("TAG", "then: " + downloadUri.toString());


//                        GeneralInfoREPO upload = new GeneralInfoREPO(mEditTextFileName.getText().toString().trim(),
//                                downloadUri.toString(),Categories,mPrepTime.getText().toString(),mCookTime.getText().toString(),mServings.getText().toString());
                        Intent intent = new Intent(AddRecipeActivity.this, AddRecipeIngreActivity.class);
                        intent.putExtra("titlepass", mEditTextFileName.getText().toString());
                        intent.putExtra("imagepass", downloadUri.toString());
                        intent.putExtra("catepass", Categories);
                        intent.putExtra("preppass", mPrepTime.getText().toString());
                        intent.putExtra("cookpass", mCookTime.getText().toString());
                        intent.putExtra("servpass", mServings.getText().toString());
                        startActivity(intent);

//                        mDatabaseRef.push().setValue(upload);
                    } else {
                        Toast.makeText(AddRecipeActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
