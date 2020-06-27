package com.example.GoCookApp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.GoCookApp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

public class Profile extends AppCompatActivity {

    static final int GOOGLE_SIGN_IN = 123;
    FirebaseAuth mAuth;
    Button btn_login, btn_logout;
    TextView text, register;
    ImageView image;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    EditText emailtext, passtext, editemail, editnama;
    Button login, edit, submitedit;
    String names = null, emails = null, providerId = null, uid = null;
    Uri photoUrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        login = findViewById(R.id.btn_login_email);
        btn_login = findViewById(R.id.btn_login);
        btn_logout = findViewById(R.id.btn_logout);
        text = findViewById(R.id.text);
        image = findViewById(R.id.image);
        progressBar = findViewById(R.id.progress_circular);
        emailtext = findViewById(R.id.email);
        passtext= findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        edit = findViewById(R.id.btn_edit);
editemail = findViewById(R.id.emailuser);
editnama = findViewById(R.id.nama);
submitedit = findViewById(R.id.btn_submitedit);
editnama.setFocusable(false);
        editemail.setFocusable(false);

        register = findViewById(R.id.registerText);
        register.setOnClickListener(v->{
            Intent intent = new Intent(Profile.this, RegisterActivity.class);
            startActivity(intent);
        });
        login.setOnClickListener(v -> {
            String emailuser = emailtext.getText().toString();
            String passuser = passtext.getText().toString();
            if (emailuser.equals("")){
                Toast.makeText(this, "Please input Your Email", Toast.LENGTH_SHORT).show();
            }else{
                if (passuser.equals("") ){
                    Toast.makeText(this, "Please input Your Password", Toast.LENGTH_SHORT).show();
                }else{
                    Signin(emailuser, passuser);
                }
            }


        });
        edit.setOnClickListener(v ->{
            editnama.setFocusableInTouchMode(true);
            editnama.setInputType(InputType.TYPE_CLASS_TEXT);
            edit.setEnabled(false);
            submitedit.setVisibility(View.VISIBLE);
        });
        submitedit.setOnClickListener(v ->{
            edit.setEnabled(true);
            String nama_baru, photo_baru;
            nama_baru = editnama.getText().toString();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nama_baru)
                    .build();
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "User profile updated.");
                                submitedit.setVisibility(View.INVISIBLE);
                                editnama.setFocusable(false);
                                editemail.setFocusable(false);
                            }
                        }
                    });
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        btn_login.setOnClickListener(v -> SignInGoogle());
        btn_logout.setOnClickListener(v -> Logout());

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(Profile.this, ProfileFinalActivity.class);
            startActivity(intent);
        }else{

        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Profile.this, MasterActivity.class);
        startActivity(intent);
    }

    public void SignInGoogle() {
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.d("TAG", "signInWithCredential:success");
                            Intent intent = new Intent(Profile.this, ProfileFinalActivity.class);
                            startActivity(intent);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                            Toast.makeText(Profile.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult();
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

  /*  private void updateUI(FirebaseUser user) {
        if (user != null) {

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                providerId = profile.getProviderId();

                // UID specific to the provider
                uid = profile.getUid();

                // Name, email address, and profile photo Url
                names = profile.getDisplayName();
                emails = profile.getEmail();
                photoUrl = profile.getPhotoUrl();
            }
            text.setText("User Info : \n");
            editnama.setText(names);
            editemail.setText(emails);
            Picasso.with(getBaseContext()).load(photoUrl).into(image);
            editemail.setVisibility(View.VISIBLE);
            editnama.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
            emailtext.setVisibility(View.INVISIBLE);
            passtext.setVisibility(View.INVISIBLE);
            login.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.VISIBLE);
        } else {
            text.setText("Firebase Login \n");
            Picasso.with(getBaseContext()).load(R.drawable.gocooktest).into(image);
            editemail.setVisibility(View.INVISIBLE);
            editnama.setVisibility(View.INVISIBLE);
            btn_logout.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.VISIBLE);
            emailtext.setVisibility(View.VISIBLE);
            passtext.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            edit.setVisibility(View.INVISIBLE);

        }
    }*/


    private void Logout() {
        FirebaseAuth.getInstance().signOut();
      //  mGoogleSignInClient.signOut().addOnCompleteListener(this,
            //    task -> updateUI(null));
    }
private void Signin (String email, String password) {
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(Profile.this, ProfileFinalActivity.class);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(Profile.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        emailtext.setText(null);
                        passtext.setText(null);
                        //updateUI(null);
                        // ...
                    }

                    // ...
                }
            });
        }
}
