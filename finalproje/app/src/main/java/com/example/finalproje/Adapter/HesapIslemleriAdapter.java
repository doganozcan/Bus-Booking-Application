package com.example.finalproje.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproje.Fragments.BiletFragment;
import com.example.finalproje.Fragments.HesapFragment;
import com.example.finalproje.MainActivity;
import com.example.finalproje.Models.HesapIslemModels;
import com.example.finalproje.R;

import java.util.ArrayList;

public class HesapIslemleriAdapter extends RecyclerView.Adapter<HesapIslemleriAdapter.ViewHolder> {

    ArrayList<HesapIslemModels> dataholder;
    Context context;

    public HesapIslemleriAdapter(ArrayList dataholder,Context context) {
        this.dataholder = dataholder;
        this.context=context;
    }


    @NonNull
    @Override
    public HesapIslemleriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hesap_islemleri,parent,false);
        return new HesapIslemleriAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HesapIslemleriAdapter.ViewHolder holder,int position) {
        HesapIslemModels hesapIslemModelsList=dataholder.get(position);

        holder.imgLogo.setImageResource(dataholder.get(position).getImgIslemler());
        holder.txtHesapIslem.setText(dataholder.get(position).getIslemler());

        switch (hesapIslemModelsList.getIslemler()){
            case "Bilet İşlemleri":



        }

    }
    @Override
    public int getItemCount() {
        return  dataholder.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLogo;
        TextView txtHesapIslem;
        CardView cardViewH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo=itemView.findViewById(R.id.imgFragHesapIslem);
            txtHesapIslem=itemView.findViewById(R.id.txtHesapIslem);
            cardViewH=itemView.findViewById(R.id.Hesap_CardView);
        }
    }
}


