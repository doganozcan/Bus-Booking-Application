package com.example.finalproje.Models;

public class BiletIslemModels {
    String Durum,logoBiletIslem, Nerden_Nereye, tamTarihSaat, Ad_Soyad, PnrNo, KoltukNo, Fiyat,uId;  // buraya giren kullanıcı ID side gelebilir çünkü bilet işlemleri listeleyeceğim zaman giren kullanıcının bilgisine göre yapacağım


    public BiletIslemModels(String durum, String nerden_Nereye, String tamTarihSaat, String ad_Soyad, String pnrNo, String koltukNo, String fiyat, int logoBiletIslem) {
        this.Durum = durum;
        this.Nerden_Nereye = nerden_Nereye;
        this.tamTarihSaat = tamTarihSaat;
        this.Ad_Soyad = ad_Soyad;
        this.PnrNo = pnrNo;
        this.KoltukNo = koltukNo;
        this.Fiyat = fiyat;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getLogoBiletIslem() {
        return logoBiletIslem;
    }

    public void setLogoBiletIslem(String logoBiletIslem) {
        this.logoBiletIslem = logoBiletIslem;
    }

    public BiletIslemModels() {
    }

    public String getDurum() {
        return Durum;
    }

    public void setDurum(String durum) {
        Durum = durum;
    }

    public String getNerden_Nereye() {
        return Nerden_Nereye;
    }

    public void setNerden_Nereye(String nerden_Nereye) {
        Nerden_Nereye = nerden_Nereye;
    }

    public String getTamTarihSaat() {
        return tamTarihSaat;
    }

    public void setTamTarihSaat(String tamTarihSaat) {
        this.tamTarihSaat = tamTarihSaat;
    }

    public String getAd_Soyad() {
        return Ad_Soyad;
    }

    public void setAd_Soyad(String ad_Soyad) {
        Ad_Soyad = ad_Soyad;
    }

    public String getPnrNo() {
        return PnrNo;
    }

    public void setPnrNo(String pnrNo) {
        PnrNo = pnrNo;
    }

    public String getKoltukNo() {
        return KoltukNo;
    }

    public void setKoltukNo(String koltukNo) {
        KoltukNo = koltukNo;
    }

    public String getFiyat() {
        return Fiyat;
    }

    public void setFiyat(String fiyat) {
        Fiyat = fiyat;
    }


}
