package com.example.finalproje;

import static io.perfmark.PerfMark.setEnabled;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproje.Adapter.BusCompaniesAdapter;
import com.example.finalproje.Fragments.BiletFragment;
import com.example.finalproje.Fragments.BusFragment;
import com.example.finalproje.Fragments.HesapFragment;
import com.example.finalproje.Fragments.KesfetFragment;
import com.example.finalproje.Fragments.SeferAraFragment;
import com.example.finalproje.Models.BiletIslemModels;
import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.Models.model;
import com.example.finalproje.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new SeferAraFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.nav_seferAra:
                    replaceFragment(new SeferAraFragment());
                    break;
                case R.id.nav_bilet_islemleri:
                    replaceFragment(new BiletFragment());
                    break;
                case R.id.nav_kesfet:
                    replaceFragment(new KesfetFragment());
                    break;
                case R.id.nav_hesabım:
                    replaceFragment(new HesapFragment());
                    break;
            }
            return true;
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
   /* int sayac=0;
    Boolean kontrol;

    public Boolean getKontrol() {
        return kontrol;
    }

    public void setKontrol(Boolean kontrol) {
        this.kontrol = kontrol;
    }

    public void  btnTıkla(View view){
        //Toast.makeText(this, ""+ view.getTag(), Toast.LENGTH_SHORT).show();
        //view.setBackgroundColor(Color.BLUE);

        //Button gelenBtn=(Button)view;
        sayac++;

        kontrol=true;
        setKontrol(kontrol);
        view.setBackgroundColor(Color.BLUE);

        if(sayac==2){
            view.setBackgroundColor(Color.BLACK);
            kontrol=false;
            setKontrol(kontrol);
            sayac=0;
        }

        BusFragment x=new BusFragment();
        x.setBtntag(view.getTag().toString());
        x.setBtnKontrol(getKontrol());
    }*/
}