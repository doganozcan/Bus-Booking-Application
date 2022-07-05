package com.example.finalproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.finalproje.Adapter.BusCompaniesAdapter;
import com.example.finalproje.Fragments.SeferAraFragment;
import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.Models.model;
import com.example.finalproje.databinding.ActivityMainBinding;
import com.example.finalproje.databinding.ActivitySignInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN=65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView imageView= findViewById(R.id.imageGif);
        Glide.with(this).asGif().load(R.drawable.bus2).into(imageView);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();



        // En son açılan hesabı sürekli açık tutar
        /*if(mAuth.getCurrentUser() != null){
            Intent myInt=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(myInt);
        }*/

        binding.lblHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(myInt);
                finish();
            }
        });


        // Google girişi için Configuration (Yapılandırma)
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        progressDialog=new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Giriş Yapılıyor");
        progressDialog.setMessage("Lütfen Bekleyin\nDoğrulama Devam Ediyor");


        binding.btnGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Text alanlarının kontrolü ve giriş
                if (!binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty()) {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(binding.txtEmail.getText().toString(), binding.txtPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignInActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(SignInActivity.this, "Boş Alan Bırakmayın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}