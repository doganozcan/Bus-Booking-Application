package com.example.finalproje.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproje.Adapter.BiletIslemleriAdapter;
import com.example.finalproje.Adapter.BusCompaniesAdapter;
import com.example.finalproje.Adapter.HesapIslemleriAdapter;
import com.example.finalproje.Models.BiletIslemModels;
import com.example.finalproje.Models.HesapIslemModels;
import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BiletFragment extends Fragment {
    DatabaseReference databaseReference;
    BiletIslemleriAdapter adapter;
    ArrayList<BiletIslemModels> dataholder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bilet, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.Bilet_recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        dataholder = new ArrayList<>();
        adapter = new BiletIslemleriAdapter(dataholder, getContext());
        recyclerView.setAdapter(adapter);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Biletler").getRef();

        // satın alınan biletleri adaptera gönderir
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BiletIslemModels x = snapshot1.getValue(BiletIslemModels.class);
                    //x.setuId(snapshot1.getKey());

                    String[] sfrSaat= x.getTamTarihSaat().split(" ");
                    sfrSaat[1]=sfrSaat[1].replace(".",":");
                    Date date = new Date();
                    Date sfrst = null;
                    Date şimdikiZaman = null;
                    Date şimdikiTarih=null;
                    Date seferTarihi=null;
                    SimpleDateFormat sss = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat tarih = new SimpleDateFormat("d/M/yyyy");

                    String sys = sss.format(date);
                    String trh=tarih.format(date);
                    try {
                        sfrst = sss.parse(sfrSaat[1]);
                        şimdikiZaman= sss.parse(sys);
                        şimdikiTarih=tarih.parse(trh);
                        seferTarihi=tarih.parse(sfrSaat[0]);

                        if(sfrst.getTime() > şimdikiZaman.getTime() && seferTarihi.compareTo(şimdikiTarih)>=0){
                            //Toast.makeText(getContext(), "GERÇEKLEŞMEDİ"+sfrSaat[1], Toast.LENGTH_SHORT).show();
                        }
                        else{
                            x.setDurum("Sefer Gerçekleşti");
                            //Toast.makeText(getContext(), "GERÇEKLEŞDİ"+sfrSaat[1], Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }


                    if (x.getuId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                          dataholder.add(x);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
    public void  adddd(View view){}
}