package com.example.finalproje.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.R;
import com.example.finalproje.SignUpActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class KullaniciFragment extends Fragment {
    DatePickerDialog.OnDateSetListener dateSetListener;
    FirebaseDatabase database;
    TextInputEditText AdSoyad,txtTC,txtTelefon,txtEmail;
    TextView dgnTarih2;
    RadioButton rb_erkek2,rb_kadın2;
    RadioGroup radiogroup;
    Button btnGuncelle;
    String rb_cinsiyet="boş";
    TextView kontrol;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kullanici, container, false);


        database= FirebaseDatabase.getInstance();
        AdSoyad=view.findViewById(R.id.txtAdSoyad);
        txtTC=view.findViewById(R.id.txtTC);
        dgnTarih2=view.findViewById(R.id.dgnTarih2);
        txtTelefon=view.findViewById(R.id.txtTelefon);
        txtEmail=view.findViewById(R.id.txtEmail2);
        rb_erkek2=view.findViewById(R.id.rbErkek2);
        rb_kadın2=view.findViewById(R.id.rbKadın2);
        btnGuncelle=view.findViewById(R.id.btnGuncelle);
        radiogroup=view.findViewById(R.id.rb_group2);
        kontrol=view.findViewById(R.id.kontrol);

        TextView dgnTarih=view.findViewById(R.id.dgnTarih2);
        dgnTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog,dateSetListener,year,month,day);
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
                dgnTarih.setText(date);
            }
        };


        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        YolcuModels users= snapshot.getValue(YolcuModels.class);

                        AdSoyad.setText(users.getAd_Soyad());
                        txtTC.setText(users.getTC());
                        dgnTarih2.setText(users.getDogumTarihi());
                        txtTelefon.setText(users.getCepTelefonu());
                        txtEmail.setText(users.getEmail());
                        if(users.getCinsiyet().equals("Erkek")){
                            rb_erkek2.setChecked(true);
                        }
                        else if(users.getCinsiyet().equals("Kadın")){
                            rb_kadın2.setChecked(true);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbErkek2:
                        kontrol.setText("Erkek");
                        break;
                    case R.id.rbKadın2:
                        kontrol.setText("Kadın");
                        break;
                }
            }
        });

        //   UPDATE
        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!AdSoyad.getText().toString().equals("")  && !txtTC.getText().toString().equals("") && !dgnTarih2.getText().toString().equals("") && !txtTelefon.getText().toString().equals("")){
                    //Toast.makeText(getContext(),kontrol.getText().toString(), Toast.LENGTH_SHORT).show();
                    String ad_Soyad= AdSoyad.getText().toString();
                    String tc= txtTC.getText().toString();
                    String dogumTarihi= dgnTarih2.getText().toString();
                    String cepTelefonu= txtTelefon.getText().toString();

                    HashMap<String,Object> obj= new HashMap<>();
                    obj.put("ad_Soyad",ad_Soyad);
                    obj.put("tc",tc);
                    obj.put("dogumTarihi",dogumTarihi);
                    obj.put("cepTelefonu",cepTelefonu);
                    obj.put("cinsiyet",kontrol.getText().toString());


                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                            .updateChildren(obj);

                    Toast.makeText(getContext(), "BİLGİLER GÜNCELLENDİ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AdSoyad.getContext(), "BOŞ ALAN BIRAKMAYIN!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}