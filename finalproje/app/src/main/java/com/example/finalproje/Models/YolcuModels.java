package com.example.finalproje.Models;

public class YolcuModels {
    String  Ad_Soyad,DogumTarihi,TC,Cinsiyet,CepTelefonu,Email,Sifre,userId;

    public YolcuModels() {
    }

    public YolcuModels(String Ad_Soyad, String dogumTarihi, String TC, String cinsiyet, String cepTelefonu, String email, String sifre) {
        this.Ad_Soyad=Ad_Soyad;
        this.DogumTarihi = dogumTarihi;
        this.TC = TC;
        this.Cinsiyet = cinsiyet;
        this.CepTelefonu = cepTelefonu;
        this.Email = email;
        this.Sifre = sifre;
        this.userId=userId;
    }

    public YolcuModels(String email, String sifre) {
        this.Email = email;
        this.Sifre = sifre;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAd_Soyad() {
        return Ad_Soyad;
    }

    public void setAd_Soyad(String ad_Soyad) {
        Ad_Soyad = ad_Soyad;
    }

    public String getDogumTarihi() {
        return DogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        DogumTarihi = dogumTarihi;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getCinsiyet() {
        return Cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        Cinsiyet = cinsiyet;
    }

    public String getCepTelefonu() {
        return CepTelefonu;
    }

    public void setCepTelefonu(String cepTelefonu) {
        CepTelefonu = cepTelefonu;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }
}
