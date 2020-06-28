package com.example.GoCookApp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.GoCookApp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.example.GoCookApp.R;

import java.util.UUID;

public class ProfileFinalActivity extends AppCompatActivity {
    static final int GOOGLE_SIGN_IN = 123;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button btn_login, btn_logout;
    TextView text;
    ImageView image;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    EditText emailtext, passtext, editemail, editnama;
    Button addrecipe, edit, submitedit, uploadphoto;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilefinal);
        btn_logout = findViewById(R.id.logoutbtnid);
        edit = findViewById(R.id.editprofileid);
        editemail = findViewById(R.id.emailid1);
        editnama = findViewById(R.id.nameid1);
        submitedit = findViewById(R.id.submiteditid);
        addrecipe = findViewById(R.id.addrecipebtnid);
        submitedit.setVisibility(View.INVISIBLE);
        image = findViewById(R.id.imageid);
        uploadphoto = findViewById(R.id.upload);
        addrecipe = findViewById(R.id.addrecipebtnid);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference("Users/" + user.getUid() + "/" + "profilepicture");
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photo = user.getPhotoUrl();
            editemail.setText(email);
            editnama.setText(name);
            Picasso.with(getBaseContext()).load(photo).into(image);
            Log.i("tag", name +"   "+ email +" " + photo);
        } else {
            Intent intent = new Intent(ProfileFinalActivity.this,Profile.class);
            startActivity(intent);
        };

        image = findViewById(R.id.imageid);

        addrecipe.setOnClickListener(v->{
            Intent intent = new Intent(ProfileFinalActivity.this,AddRecipeActivity.class);
            startActivity(intent);
        });

        edit.setOnClickListener(v -> {
            submitedit.setVisibility(View.VISIBLE);
            uploadphoto.setVisibility(View.VISIBLE);
        });

        uploadphoto.setOnClickListener(v->{
           openFileChooser();
        });
        submitedit.setOnClickListener(v -> {
            edit.setEnabled(true);
            mStorageRef.putFile(mImageUri).continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return mStorageRef.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.i("TAG", task.getResult().toString());
                    Uri downloadUri = task.getResult();
                    Log.i("TAG", "then: " + downloadUri.toString());
                    String nama_baru;
                    nama_baru = editnama.getText().toString();
                    FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(nama_baru)
                            .setPhotoUri(downloadUri)
                            .build();
                    user1.updateProfile(profileUpdates)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Log.i("TAG", "User profile updated.");
                                    submitedit.setVisibility(View.INVISIBLE);
                                    editnama.setFocusable(false);
                                    editemail.setFocusable(false);
                                    Intent intent = new Intent(ProfileFinalActivity.this,ProfileFinalActivity.class);
                                    startActivity(intent);
                                } else{
                                    Log.i("TAG", "error");
                                }
                            });

                }else{
                    Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        addrecipe.setOnClickListener(v->{
            Intent intent = new Intent(ProfileFinalActivity.this , AddRecipeActivity.class);
            startActivity(intent);
        });

        btn_logout.setOnClickListener(v -> Logout());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileFinalActivity.this, MasterActivity.class));
    }


    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ProfileFinalActivity.this, Profile.class);
        startActivity(intent);
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
            Picasso.with(this).load(mImageUri).centerCrop().resize(120,120).into(image);
        }
    }


}
