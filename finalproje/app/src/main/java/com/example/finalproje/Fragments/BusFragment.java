package com.example.finalproje.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproje.Adapter.BusCompaniesAdapter;
import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BusFragment extends Fragment {
    Animation rotateAnimation;
    ImageView img;
    TextView Nerden, Nereye, txtFraBusTarih;

    String sfr, nerden, nereye, Btntag,ad_SOY;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    BusCompaniesAdapter adapter;
    ArrayList<SeferModels> dataholder;

    Boolean btnKontrol;


    public String getAd_SOY() {
        return ad_SOY;
    }

    public void setAd_SOY(String ad_SOY) {
        this.ad_SOY = ad_SOY;
    }

    public String getNerden() {
        return nerden;
    }

    public void setNerden(String nerden) {
        this.nerden = nerden;
    }

    public String getNereye() {
        return nereye;
    }

    public void setNereye(String nereye) {
        this.nereye = nereye;
    }

    public String getSfr() {
        return sfr;
    }

    public void setSfr(String sfr) {
        this.sfr = sfr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus, container, false);
        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()/*this.getContext()*/));

        txtFraBusTarih = view.findViewById(R.id.txtFraBusTarih);
        txtFraBusTarih.setText(getSfr());

        img = view.findViewById(R.id.imgFragBusYon);
        Nerden = view.findViewById(R.id.txtFraBusNerden);
        Nereye = view.findViewById(R.id.txtFraBusNereye);


        dataholder = new ArrayList<>();
        adapter = new BusCompaniesAdapter(getContext(), dataholder);
        recyclerView.setAdapter(adapter);


        adapter.setSfrTarih(getSfr());
        adapter.setNerden(getNerden());
        adapter.setNereye(getNereye());
        adapter.setAdS(getAd_SOY());


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Seferler");

        // seferleri adaptera gönderir
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    SeferModels x = snapshot1.getValue(SeferModels.class);
                    x.setId(snapshot1.getKey());
                    if (x.getSeferTarihi().equals(getSfr()) && x.getNerden().equals(getNerden())&& x.getNereye().equals(nereye))
                        dataholder.add(x);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Nerden.setText(getNerden());
        Nereye.setText(getNereye());

        String kaliciNerden, kaliciNereye;
        kaliciNerden = Nerden.getText().toString();
        kaliciNereye = Nereye.getText().toString();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
                img.startAnimation(rotateAnimation);

                if (Nerden.getText().toString().equals(kaliciNerden)) {
                    Nerden.setText(kaliciNereye);
                    Nereye.setText(kaliciNerden);
                } else {
                    Nerden.setText(kaliciNerden);
                    Nereye.setText(kaliciNereye);
                }

                BusFragment x = new BusFragment();
                x.setSfr(getSfr());
                x.setNerden(Nerden.getText().toString());
                x.setNereye(Nereye.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, x, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

}