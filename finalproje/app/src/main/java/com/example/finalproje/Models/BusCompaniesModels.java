package com.example.finalproje.Models;

public class BusCompaniesModels {
    private String firmaLogo,firmaAd,sahibiAdSoyad,firmaSahibiResim,sahibiTelefonNo,sahibiEmail,sahibiSifre;

    public BusCompaniesModels(String firmaLogo, String firmaAd, String sahibiAdSoyad, String firmaSahibiResim, String sahibiTelefonNo, String sahibiEmail, String sahibiSifre) {
        this.firmaLogo = firmaLogo;
        this.firmaAd = firmaAd;
        this.sahibiAdSoyad = sahibiAdSoyad;
        this.firmaSahibiResim = firmaSahibiResim;
        this.sahibiTelefonNo = sahibiTelefonNo;
        this.sahibiEmail = sahibiEmail;
        this.sahibiSifre = sahibiSifre;
    }

    public String getFirmaLogo() {
        return firmaLogo;
    }

    public void setFirmaLogo(String firmaLogo) {
        this.firmaLogo = firmaLogo;
    }

    public String getFirmaAd() {
        return firmaAd;
    }

    public void setFirmaAd(String firmaAd) {
        this.firmaAd = firmaAd;
    }

    public String getSahibiAdSoyad() {
        return sahibiAdSoyad;
    }

    public void setSahibiAdSoyad(String sahibiAdSoyad) {
        this.sahibiAdSoyad = sahibiAdSoyad;
    }

    public String getFirmaSahibiResim() {
        return firmaSahibiResim;
    }

    public void setFirmaSahibiResim(String firmaSahibiResim) {
        this.firmaSahibiResim = firmaSahibiResim;
    }

    public String getSahibiTelefonNo() {
        return sahibiTelefonNo;
    }

    public void setSahibiTelefonNo(String sahibiTelefonNo) {
        this.sahibiTelefonNo = sahibiTelefonNo;
    }

    public String getSahibiEmail() {
        return sahibiEmail;
    }

    public void setSahibiEmail(String sahibiEmail) {
        this.sahibiEmail = sahibiEmail;
    }

    public String getSahibiSifre() {
        return sahibiSifre;
    }

    public void setSahibiSifre(String sahibiSifre) {
        this.sahibiSifre = sahibiSifre;
    }
}
