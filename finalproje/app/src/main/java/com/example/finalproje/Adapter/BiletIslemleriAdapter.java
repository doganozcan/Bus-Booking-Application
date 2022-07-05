package com.example.finalproje.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproje.Models.BiletIslemModels;
import com.example.finalproje.Models.BusCompaniesModels;
import com.example.finalproje.Models.HesapIslemModels;
import com.example.finalproje.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BiletIslemleriAdapter extends RecyclerView.Adapter<BiletIslemleriAdapter.ViewHolder> {

    ArrayList<BiletIslemModels> dataholder;
    Context context;

    public BiletIslemleriAdapter( ArrayList<BiletIslemModels> dataholder,Context context){
        this.dataholder=dataholder;
        this.context=context;
    }

    @NonNull
    @Override
    public BiletIslemleriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bilet_islemleri,parent,false);
        return new BiletIslemleriAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BiletIslemModels biletIslemModels=dataholder.get(position);

        Glide.with(holder.logoBusCom.getContext()).load(biletIslemModels.getLogoBiletIslem()).into(holder.logoBusCom);
        holder.durum.setText(biletIslemModels.getDurum());
        holder.hareketBilgi.setText(biletIslemModels.getNerden_Nereye());
        holder.hareketTarih.setText(biletIslemModels.getTamTarihSaat());
        holder.adSoyad.setText(biletIslemModels.getAd_Soyad());
        holder.pnrNo.setText(biletIslemModels.getPnrNo());
        holder.koltukNo.setText(biletIslemModels.getKoltukNo());
        holder.fiyat.setText(biletIslemModels.getFiyat().replace("TL","₺"));
        holder.toplamTutar.setText(biletIslemModels.getFiyat());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView logoBusCom;
        TextView durum,hareketBilgi,hareketTarih,adSoyad,pnrNo,koltukNo,fiyat,toplamTutar; //
        Button btnOnayla;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnOnayla=itemView.findViewById(R.id.btnOnayla);
            logoBusCom=itemView.findViewById(R.id.imgFragBiletIslem);
            durum=itemView.findViewById(R.id.txtBiletDurum);
            hareketBilgi=itemView.findViewById(R.id.txtHareketBilgi);
            hareketTarih=itemView.findViewById(R.id.txtHareketTarih);
            adSoyad=itemView.findViewById(R.id.txtBiletAdSoyad);
            pnrNo=itemView.findViewById(R.id.txtPnrNo);
            koltukNo=itemView.findViewById(R.id.txtkoltukNo);
            fiyat=itemView.findViewById(R.id.txtBiletFiyat);
            toplamTutar=itemView.findViewById(R.id.txttoplamTutar);
        }
    }    public void  adddd(View view){}
}
