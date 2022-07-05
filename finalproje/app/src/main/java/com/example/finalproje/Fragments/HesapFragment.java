package com.example.finalproje.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproje.Adapter.BusCompaniesAdapter;
import com.example.finalproje.Adapter.HesapIslemleriAdapter;
import com.example.finalproje.Models.BusCompaniesModels;
import com.example.finalproje.Models.HesapIslemModels;
import com.example.finalproje.R;
import com.example.finalproje.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HesapFragment extends Fragment {
    TextView blt;
    RelativeLayout biletIslemleri,kullaniciBilgileri,cikisYap,btnHakkimizda,paylas;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hesap, container, false);

        mAuth= FirebaseAuth.getInstance();

        biletIslemleri=view.findViewById(R.id.rltv);
        biletIslemleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BiletFragment nextFrag= new BiletFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        kullaniciBilgileri=view.findViewById(R.id.rltv2);
        kullaniciBilgileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KullaniciFragment nextFrag= new KullaniciFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnHakkimizda=view.findViewById(R.id.frag_hkmzda);
        btnHakkimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HakkimizdaFragment hkmzda= new HakkimizdaFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, hkmzda, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        paylas=view.findViewById(R.id.rltv6);
        paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody="Uygulamayı İndir Ve Arkadaşlarınla Konuşmaya Başla: https://drive.google.com/drive/folders/19sXJwwbauREdIX8HdBycOd_OrCtw0u_T?usp=sharing";
                String shareSub="https://drive.google.com/drive/folders/19sXJwwbauREdIX8HdBycOd_OrCtw0u_T?usp=sharing";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Kullanarak Paylaş"));
            }
        });

        cikisYap=view.findViewById(R.id.rltv5);
        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent3=new Intent(getContext(), SignInActivity.class);
                startActivity(intent3);
            }
        });



        return view;
    }
}