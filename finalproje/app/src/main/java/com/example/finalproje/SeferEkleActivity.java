package com.example.finalproje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.Models.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeferEkleActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sefer_ekle);

        /*databaseReference= FirebaseDatabase.getInstance().getReference().child("Seferler");
        SeferModels sefer=new SeferModels("bos","İstanbul","Kastamonu","bos","09:45","1/6/2022","200 ₺","6s 45dk","35","2+1");
        databaseReference.push().setValue(sefer);



        /*for (int i = 0; i < 36; i++) {
        Boolean bool=false;
        if (i%5==0)
            bool =true;
            databaseReference= FirebaseDatabase.getInstance().getReference().child("Koltuk");
            model model1=new model(i,bool,"N3_Fk8xjK8Y6ft4Tv35");
            databaseReference.push().setValue(model1);
        }
        for (int i = 0; i < 36; i++) {
            Boolean bool=false;
            if (i%5==0)
                bool =true;
            databaseReference= FirebaseDatabase.getInstance().getReference().child("Koltuk");
            model model1=new model(i,bool,"N3PTsrB3Sz6b3sc8Kb6");
            databaseReference.push().setValue(model1);
        }*/
    }
}