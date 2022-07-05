package com.example.finalproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.databinding.ActivitySignInBinding;
import com.example.finalproje.databinding.ActivitySignUpBinding;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    private final static int RC_SIGN_IN=65;
    GoogleSignInClient mGoogleSignInClient;

    RadioGroup radioGroup;
    RadioButton radioButton;
    String rbText;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Boolean kontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        kontrol=false;
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        radioGroup=findViewById(R.id.rb_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbErkek:
                        rbText="Erkek";
                        break;
                    case R.id.rbKadın:
                        rbText="Kadın";
                        break;
                }
            }
        });


        TextView dgnTarih=findViewById(R.id.dgnTarih);
        dgnTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kontrol=true;
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(SignUpActivity.this,android.R.style.Theme_DeviceDefault_Dialog,dateSetListener,year,month,day);
                dialog.show();
            }
        });

        final String[] tmp = new String[1];
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                String date=day+"/"+month+"/"+year; //orn= 22/5/2022 bunu otobüs bulmak için kullanacağım
                tmp[0] =date;
                binding.dgnTarih.setText(date);
            }
        };




        // Google girişi için Configuration (Yapılandırma)
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Hesap Oluşturma");
        progressDialog.setMessage("Hesap Oluşturuluyor");

        /*binding.lblHesapVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(myInt);
                mAuth.signOut();
            }
        });*/

        binding.btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.email.getText().toString().isEmpty() && !binding.sifre.getText().toString().isEmpty()  && !binding.txtAd.getText().toString().isEmpty() && !binding.txtSoyad.getText().toString().isEmpty()  && kontrol  && !binding.tc.getText().toString().isEmpty()  && !(binding.rbGroup.getCheckedRadioButtonId() ==-1)  && !binding.telefonNo.getText().toString().isEmpty()  && !binding.sifre.getText().toString().isEmpty()  ){
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.sifre.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        YolcuModels user=new YolcuModels(binding.txtAd.getText().toString()+" "+binding.txtSoyad.getText().toString(),binding.dgnTarih.getText().toString(),binding.tc.getText().toString(),rbText,binding.telefonNo.getText().toString(),binding.email.getText().toString(),binding.sifre.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        user.setUserId(id);
                                        firebaseDatabase.getReference().child("Users").child(id).setValue(user);

                                        Toast.makeText(SignUpActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                                        Intent myInt=new Intent(getApplicationContext(),SignInActivity.class);
                                        startActivity(myInt);
                                    }
                                    else{
                                        Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Boş Alan Bırakmayın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnGrsYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent xd=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(xd);
            }
        });

     /*  */
    }

    private void signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override //Google girişini kontroleder.
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account =task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account); // önceden account.getIdToken() bu vardı
            }
            catch (ApiException e){
                Toast.makeText(this, "Üzgünüz Bağlantıda Sorun Var", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Google ile giriş yapan kişinin bilgilerini veritabanına kaydediyorum.
                            YolcuModels users = new YolcuModels();
                            users.setUserId(user.getUid());
                            users.setAd_Soyad(user.getDisplayName());
                            users.setEmail(user.getEmail());
                            firebaseDatabase.getReference().child("Users").child(user.getUid()).setValue(users);

                            Intent mINT = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mINT);

                            Toast.makeText(SignUpActivity.this, "Google İle Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}