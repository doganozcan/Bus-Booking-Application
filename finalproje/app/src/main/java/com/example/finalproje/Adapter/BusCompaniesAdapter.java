package com.example.finalproje.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproje.Fragments.BusFragment;
import com.example.finalproje.Fragments.HesapFragment;
import com.example.finalproje.Fragments.SeferAraFragment;
import com.example.finalproje.MainActivity;
import com.example.finalproje.Models.BiletIslemModels;
import com.example.finalproje.Models.BusCompaniesModels;
import com.example.finalproje.Models.SeferModels;
import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.Models.model;
import com.example.finalproje.R;
import com.example.finalproje.SignUpActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BusCompaniesAdapter extends RecyclerView.Adapter<BusCompaniesAdapter.ViewHolder> {
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<model> modelArrayList = new ArrayList<>();
    ArrayList<String> busSt = new ArrayList<>();
    ArrayList<model> seciliKoltuklar = new ArrayList<>();
    String secili="#373c52";
    String dolu="#194227";
    String bos="#777772";

    public ArrayList<Button> getButtons(@NonNull View itemView) {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(itemView.findViewById(R.id.btn1));
        buttons.add(itemView.findViewById(R.id.btn2));
        buttons.add(itemView.findViewById(R.id.btn3));
        buttons.add(itemView.findViewById(R.id.btn4));
        buttons.add(itemView.findViewById(R.id.btn5));
        buttons.add(itemView.findViewById(R.id.btn6));
        buttons.add(itemView.findViewById(R.id.btn7));
        buttons.add(itemView.findViewById(R.id.btn8));
        buttons.add(itemView.findViewById(R.id.btn9));
        buttons.add(itemView.findViewById(R.id.btn10));
        buttons.add(itemView.findViewById(R.id.btn11));
        buttons.add(itemView.findViewById(R.id.btn12));
        buttons.add(itemView.findViewById(R.id.btn13));
        buttons.add(itemView.findViewById(R.id.btn14));
        buttons.add(itemView.findViewById(R.id.btn15));
        buttons.add(itemView.findViewById(R.id.btn16));
        buttons.add(itemView.findViewById(R.id.btn17));
        buttons.add(itemView.findViewById(R.id.btn18));
        buttons.add(itemView.findViewById(R.id.btn19));
        buttons.add(itemView.findViewById(R.id.btn20));
        buttons.add(itemView.findViewById(R.id.btn21));
        buttons.add(itemView.findViewById(R.id.btn22));
        buttons.add(itemView.findViewById(R.id.btn23));
        buttons.add(itemView.findViewById(R.id.btn24));
        buttons.add(itemView.findViewById(R.id.btn25));
        buttons.add(itemView.findViewById(R.id.btn26));
        buttons.add(itemView.findViewById(R.id.btn27));
        buttons.add(itemView.findViewById(R.id.btn28));
        buttons.add(itemView.findViewById(R.id.btn29));
        buttons.add(itemView.findViewById(R.id.btn30));
        buttons.add(itemView.findViewById(R.id.btn31));
        buttons.add(itemView.findViewById(R.id.btn32));
        buttons.add(itemView.findViewById(R.id.btn33));
        buttons.add(itemView.findViewById(R.id.btn34));
        buttons.add(itemView.findViewById(R.id.btn35));
        buttons.add(itemView.findViewById(R.id.btn36));


        return buttons;
    }

    FirebaseAuth mAuth;
    ArrayList<SeferModels> dataholder;
    Context context;
    String sfrTarih, Nerden, Nereye, adS;
    Button btnOny;

    public String getAdS() {
        return adS;
    }

    public void setAdS(String adS) {
        this.adS = adS;
    }

    public void setNerden(String nerden) {
        Nerden = nerden;
    }

    public String getNereye() {
        return Nereye;
    }

    public void setNereye(String nereye) {
        Nereye = nereye;
    }

    public void setSfrTarih(String sfrTarih) {
        this.sfrTarih = sfrTarih;
    }

    public BusCompaniesAdapter(Context context, ArrayList<SeferModels> dataholder) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_list2, parent, false);
        Button g = view.findViewById(R.id.btn8);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SeferModels bcList = dataholder.get(position);
        seciliKoltuklar = new ArrayList<>();

        getAra(bcList.getId(), holder);
        Boolean isExpandable = dataholder.get(position).getExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.txtKoltukTipi.setText(bcList.getKoltukTipi());
        holder.txtDoluluk.setText(bcList.getKapasite());
        holder.txtYolculukSüresi.setText(bcList.getYolculukSuresi());
        holder.txtNerdenNereye.setText(bcList.getNerden() + " > " + bcList.getNereye());
        holder.txtFiyat.setText(bcList.getFiyat().replace("TL","₺"));
        holder.txtSaat.setText(bcList.getSaat());
        Glide.with(holder.imgBusLogo.getContext()).load(bcList.getFirmaLogo()).placeholder(R.drawable.logoyok).into(holder.imgBusLogo);




        holder.btnOnayla.setTag(bcList);
        holder.btnOnayla.setEnabled(false);
        holder.btnOnayla.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Bilet Al");
                alert.setMessage("Bileti satın alınsın mı?");
                alert.setCancelable(false);
                alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SeferModels m = (SeferModels) view.getTag();
                        al(m,seciliKoltuklar.get(0));
                        Toast.makeText(context, "Bilet Satın Alındı", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                alert.show();
            }
            // dinamik buton
        /*Button button=new Button(holder.expandableLayout.getContext());
        button.setText("31 Software katkılarıyla");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                50,
               50
        );

        /*
        android:layout_alignParentTop="true"
                        android:layout_marginStart="120dp"
                        android:layout_marginTop="217dp"
                        android:background="@drawable/ripple_effect2"
                        android:backgroundTint="@color/black"

        button.setTag(position+"-"+1);
        button.setOnClickListener(this::onclick);
        params.topMargin=200;
        params.width=350;
        params.height=200;
        params.setMarginStart(200);
        button.setLayoutParams(params);
        holder.expandableLayout.addView(button);*/

        });
    }
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void al(SeferModels seferModels, model koltuk) {
                koltuk.setDurum(true);
                FirebaseDatabase.getInstance().getReference().child("Koltuk").child(koltuk.getId()).setValue(koltuk);
                BiletIslemModels modelBilet = new BiletIslemModels();

                modelBilet.setuId(FirebaseAuth.getInstance().getUid());

                modelBilet.setAd_Soyad(getAdS());
                modelBilet.setDurum("Sefer Henüz Gerçekleşmedi");
                modelBilet.setFiyat(seferModels.getFiyat());
                modelBilet.setLogoBiletIslem(seferModels.getFirmaLogo());
                modelBilet.setKoltukNo(koltuk.getKoltukNo() + "");
                modelBilet.setNerden_Nereye(seferModels.getNerden() + " - " + seferModels.getNereye());
                modelBilet.setPnrNo(seferModels.getSaat() + koltuk.getKoltukNo() + seferModels.getId().substring(3, 5));
                modelBilet.setTamTarihSaat(seferModels.getSeferTarihi() + " " + seferModels.getSaat());

                FirebaseDatabase.getInstance().getReference().child("Biletler").push().setValue(modelBilet);
            }

            public void getAra(String seferid, ViewHolder holder_) {

                DatabaseReference databaseReference = FirebaseDatabase
                        .getInstance().getReference().child("Koltuk").orderByChild("seferid").equalTo(seferid).getRef();
                ColorReset();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { //addValueEventListener
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            model n = snapshot1.getValue(model.class);
                            n.setId(snapshot1.getKey());

                            if (n.getSeferid().equals(seferid)) {
                                for (Button button : buttons) {
                                    button.setOnClickListener(BusCompaniesAdapter.this::btnTikla);
                                    if (button.getText().toString().trim().equals(n.getKoltukNo().toString())) {
                                        if (n.getDurum()) {
                                            button.setBackgroundColor(Color.parseColor(dolu));

                                        } else
                                            button.setBackgroundColor(Color.parseColor(bos));
                                        button.setTag(n);

                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            public void ColorReset() {
                for (Button btn : buttons

                ) {
                    btnOny.setBackgroundColor(Color.parseColor(bos));

                }
            }

            public void btnTikla(View view) {
                //Toast.makeText(view.getContext(), ""+ view.getTag().toString(), Toast.LENGTH_SHORT).show();

                model ff = (model) view.getTag();

                ColorDrawable viewColor = (ColorDrawable) view.getBackground();
                int colorId = viewColor.getColor();

                if (!ff.getDurum())
                    if (colorId == Color.parseColor(secili)) {
                        view.setBackgroundColor(Color.parseColor(bos));


                        for (int i = 0; i < seciliKoltuklar.size(); i++) {
                            if (seciliKoltuklar.get(i).getKoltukNo().equals(ff.getKoltukNo())) {
                                seciliKoltuklar.remove(i);
                                break;
                            }
                        }
                    } else {
                        seciliKoltuklar.add(ff);
                        view.setBackgroundColor(Color.parseColor(secili));


                    }
                if (seciliKoltuklar.size() > 0)
                    btnOny.setEnabled(true);
                else
                    btnOny.setEnabled(false);
            }

            @Override
            public int getItemCount() {
                return dataholder.size();
            }


            public class ViewHolder extends RecyclerView.ViewHolder {
                ImageView imgBusLogo, bus_layout, loading_gif, ImageVDoubleArrow;
                TextView txtSaat, seferTarih, txtFiyat, txtYolculukSüresi, txtDoluluk, txtKoltukTipi, txtNerdenNereye,txtKontrol;
                TextView tw_nerdenNereye, tw_koltukNo, tw_saat, tw_fiyat;

                Spinner spNerden;
                LinearLayout linearLayout;
                RelativeLayout expandableLayout;
                Button btnOnayla, btn5, btn_biletOnay, btn_biletKapat,btnGit;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tw_nerdenNereye = itemView.findViewById(R.id.tw_nerdenNereye);
                    tw_koltukNo = itemView.findViewById(R.id.tw_koltukNo);
                    tw_saat = itemView.findViewById(R.id.tw_saat);
                    tw_fiyat = itemView.findViewById(R.id.tw_fiyat);

                    btn_biletOnay = itemView.findViewById(R.id.btn_biletOnay);
                    btn_biletKapat = itemView.findViewById(R.id.btn_biletKapat);
                    btn5 = itemView.findViewById(R.id.btn5);
                    buttons = getButtons(itemView);
                    btnOnayla = itemView.findViewById(R.id.btnOnayla);
                    btnOny = btnOnayla;
                    seferTarih = itemView.findViewById(R.id.txtTemp);
                    loading_gif = itemView.findViewById(R.id.loading_gif);
                    imgBusLogo = itemView.findViewById(R.id.imgBusLogo);
                    bus_layout = itemView.findViewById(R.id.imgBusLayout);
                    txtSaat = itemView.findViewById(R.id.txtSaat);
                    txtFiyat = itemView.findViewById(R.id.txtFiyat);
                    txtYolculukSüresi = itemView.findViewById(R.id.txtYolculukSüresi);
                    txtDoluluk = itemView.findViewById(R.id.txtDoluluk);
                    txtKoltukTipi = itemView.findViewById(R.id.txtKoltukTipi);
                    txtNerdenNereye = itemView.findViewById(R.id.txtNerdenNereye);
                    spNerden = itemView.findViewById(R.id.spNerden);


                    linearLayout = itemView.findViewById(R.id.linear_layout);
                    expandableLayout = itemView.findViewById(R.id.expandable_layout);

                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SeferModels modelNesnesi = dataholder.get(getAdapterPosition());
                            modelNesnesi.setExpandable(!modelNesnesi.getExpandable());
                            notifyItemChanged(getAdapterPosition());
                        }
                    });

                    ImageView imageView = itemView.findViewById(R.id.loading_gif);
                    bus_layout = itemView.findViewById(R.id.imgBusLayout);
                    ImageVDoubleArrow = itemView.findViewById(R.id.doubleArrowGif);
                    RelativeLayout x = itemView.findViewById(R.id.SecimLayout);
                    new CountDownTimer(3000, 1500) {
                        @Override
                        public void onTick(long l) {
                            Glide.with(context).asGif().load(R.drawable.loading_gif).into(imageView);
                            Glide.with(context).asGif().load(R.drawable.double_arrow).into(ImageVDoubleArrow);
                        }

                        @Override
                        public void onFinish() {
                            loading_gif.invalidate();
                            loading_gif.setImageBitmap(null);

                            loading_gif.setVisibility(View.INVISIBLE);
                            x.setVisibility(View.VISIBLE);
                        }
                    }.start();


                    String bos="dasdnadnada";
                    txtKontrol.setText(bos);
                    btnGit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context, "deeenmee", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
    }
}

