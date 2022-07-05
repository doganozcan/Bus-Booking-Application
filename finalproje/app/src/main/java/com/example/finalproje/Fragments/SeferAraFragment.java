package com.example.finalproje.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproje.Models.YolcuModels;
import com.example.finalproje.R;
import com.example.finalproje.SeferEkleActivity;
import com.example.finalproje.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SeferAraFragment extends Fragment {
    private DatePickerDialog.OnDateSetListener dateSetListener;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    Spinner spNerden,spNereye;
    Animation rotateAnimation;
    TextView txtTarih,txtTemp;
    Button btnBugun,btnYarin,btnOtobusbul;
    ProgressDialog progressDialog;
    Boolean kontrol;
    String adSOY;

    public String getAdSOY() {
        return adSOY;
    }

    public void setAdSOY(String adSOY) {
        this.adSOY = adSOY;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sefer_ara, container, false);
        kontrol=false;
        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();




        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance().getReference().child("Users").getRef();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { //addValueEventListener
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    YolcuModels n = snapshot1.getValue(YolcuModels.class);

                    if(n.getUserId().equals(mAuth.getUid()))
                          setAdSOY(n.getAd_Soyad());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        spNerden=view.findViewById(R.id.spNerden);
        spNereye=view.findViewById(R.id.spNereye);

        String[] sehirler ={"Seçiniz","Adana","Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
                "Aydın", "Balıkesir","Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
                "Çankırı", "Çorum","Denizli","Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum ", "Eskişehir",
                "Gaziantep", "Giresun","Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
                "Kars", "Kastamonu", "Kayseri","Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya ", "Malatya",
                "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
                "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon  ", "Tunceli", "Şanlıurfa", "Uşak",
                "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "Kırıkkale", "Batman", "Şırnak",
                "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük ", "Kilis", "Osmaniye ", "Düzce"};
        ArrayAdapter<String> arrayNerden=new ArrayAdapter<>(getContext(), R.layout.style_spinner,sehirler);
        spNerden.setAdapter(arrayNerden);

        ArrayAdapter<String> arrayNereye=new ArrayAdapter<>(getContext(), R.layout.style_spinner,sehirler);
        spNereye.setAdapter(arrayNereye);

        ImageView yon=view.findViewById(R.id.yon);
        yon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spNerden.getSelectedItemId()!=0 && spNereye.getSelectedItemId()!=0)
                {
                    //yon.animate().setDuration(1000).rotation(180f).start();
                    rotateAnimation=AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
                    yon.startAnimation(rotateAnimation);

                    int positionSpinner1 = spNerden.getSelectedItemPosition() ;
                    int positionSpinner2 = spNereye.getSelectedItemPosition() ;
                    if (spNerden.getAdapter().equals(arrayNerden)) {
                        spNerden.setAdapter(arrayNereye);
                        spNereye.setAdapter(arrayNerden);
                    } else {
                        spNerden.setAdapter(arrayNerden);
                        spNereye.setAdapter(arrayNereye);
                    }
                    spNerden.setSelection(positionSpinner2);
                    spNereye.setSelection(positionSpinner1);
                }
                else{
                    if(spNereye.getSelectedItemId()==0)
                        Toast.makeText(getContext(), "Nereye Gideceğinizi Seçin!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Nerden Gideceğinizi Seçin!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        txtTarih=view.findViewById(R.id.txttarih);
        txtTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kontrol=true;
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog,dateSetListener,year,month,day);
                dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());  // önceki tarihlerin gösterlmemesini sağlar
                dialog.show();
            }
        });

        final String[] tmp = new String[1];
        txtTemp=view.findViewById(R.id.txtTemp);
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                String date=day+"/"+month+"/"+year; //orn= 22/5/2022 bunu otobüs bulmak için kullanacağım
                tmp[0] =date;

                String trDate=day+" "+getNameOfMonth(month)+" "+getNameOfDay(year,day);
                txtTarih.setText(trDate);
            }
        };

        btnBugun=view.findViewById(R.id.btnBugun);
        btnYarin=view.findViewById(R.id.btnYarin);

        btnBugun.setOnClickListener(new View.OnClickListener()  {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                kontrol=true;

                SimpleDateFormat dateFormat;
                String date;
                Calendar calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("dd/M/yyyy");  // çalışıyor
                date = dateFormat.format(calendar.getTime());
                String[] arr=date.split("/");
                int day=Integer.parseInt(arr[0]);
                int month=Integer.parseInt(arr[1]);
                int year=Integer.parseInt(arr[2]);
                txtTarih.setText(day+" "+getNameOfMonth(month)+" "+getNameOfDay(year,day));
                date=day+"/"+month+"/"+year; // bunu kullanıcağız

                txtTemp.setText(date);
                tmp[0] =date;
            }
        });

        btnYarin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kontrol=true;

                SimpleDateFormat dateFormat;
                String date;
                Calendar calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("dd/M/yyyy");  // çalışıyor
                date = dateFormat.format(calendar.getTime());
                String[] arr=date.split("/");
                int day=Integer.parseInt(arr[0])+1;
                int month=Integer.parseInt(arr[1]);
                int year=Integer.parseInt(arr[2]);
                txtTarih.setText(day+" "+getNameOfMonth(month)+" "+getNameOfDay(year,day));
                date=day+"/"+month+"/"+year; // bunu kullanıcağız
                txtTemp.setText(date);
                tmp[0] =date;
            }
        });


        btnOtobusbul=view.findViewById(R.id.btnOtobusBul);
        btnOtobusbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spNerden.getSelectedItemId()!=0 && spNereye.getSelectedItemId()!=0 && kontrol==true){
                    progressDialog=new ProgressDialog(getContext());
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.custom_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(
                            android.R.color.transparent
                    );
                    progressDialog.setCancelable(false);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            BusFragment nextFrag= new BusFragment();
                            //verileri BusFragmentına gönderiyorum
                            nextFrag.setAd_SOY(getAdSOY());
                            nextFrag.setSfr(tmp[0]);
                            nextFrag.setNerden(spNerden.getSelectedItem().toString());
                            nextFrag.setNereye(spNereye.getSelectedItem().toString());

                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.frame_layout, nextFrag, "findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                            progressDialog.dismiss();
                        }
                    },2000);
                }
                else if(!kontrol)
                    Toast.makeText(getContext(), "Tarih Seçiniz", Toast.LENGTH_SHORT).show();
                else{
                    if(spNerden.getSelectedItemId()==0)
                        Toast.makeText(getContext(), "Nerden Gideceğinizi Seçiniz", Toast.LENGTH_SHORT).show();
                    else if(spNereye.getSelectedItemId()==0)
                        Toast.makeText(getContext(), "Nereye Gideceğinizi Seçiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }

    public String getNameOfDay(int year, int dayOfYear) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        String[] gunler={"Pazartesi","Salı","Çarşamba","Perşembe","Cuma","Cumartesi","Pazar"};

        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);

        return gunler[dayIndex-1];
    }

    public String getNameOfMonth(int month) {
        String[] aylar={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};

        return aylar[month-1];
    }    public void  adddd(View view){}
}