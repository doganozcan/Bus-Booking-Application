package com.example.finalproje.Models;

public class SeferModels {
    String firmaLogo,nerden,nereye,gidilecekYerler,saat,seferTarihi,fiyat,yolculukSuresi,kapasite,koltukTipi;
    Boolean expandable;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SeferModels() {
    }

    public SeferModels(String firmaLogo, String nerden, String nereye, String gidilecekYerler, String saat, String seferTarihi, String fiyat, String yolculukSuresi, String kapasite, String koltukTipi) {
        this.firmaLogo = firmaLogo;
        this.nerden = nerden;
        this.nereye = nereye;
        this.gidilecekYerler = gidilecekYerler;
        this.saat = saat;
        this.seferTarihi = seferTarihi;
        this.fiyat = fiyat;
        this.yolculukSuresi = yolculukSuresi;
        this.kapasite = kapasite;
        this.koltukTipi = koltukTipi;
        this.expandable=false;
    }

    public Boolean getExpandable() {
        return expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public String getFirmaLogo() {
        return firmaLogo;
    }

    public void setFirmaLogo(String firmaLogo) {
        this.firmaLogo = firmaLogo;
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

    public String getGidilecekYerler() {
        return gidilecekYerler;
    }

    public void setGidilecekYerler(String gidilecekYerler) {
        this.gidilecekYerler = gidilecekYerler;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getSeferTarihi() {
        return seferTarihi;
    }

    public void setSeferTarihi(String seferTarihi) {
        this.seferTarihi = seferTarihi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getYolculukSuresi() {
        return yolculukSuresi;
    }

    public void setYolculukSuresi(String yolculukSuresi) {
        this.yolculukSuresi = yolculukSuresi;
    }

    public String getKapasite() {
        return kapasite;
    }

    public void setKapasite(String kapasite) {
        this.kapasite = kapasite;
    }

    public String getKoltukTipi() {
        return koltukTipi;
    }

    public void setKoltukTipi(String koltukTipi) {
        this.koltukTipi = koltukTipi;
    }
}
