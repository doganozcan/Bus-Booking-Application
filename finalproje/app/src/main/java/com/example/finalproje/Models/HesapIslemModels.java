package com.example.finalproje.Models;

public class HesapIslemModels {
    String ıslemler;
    int imgIslemler;

    public HesapIslemModels(String ıslemler, int imgIslemler) {
        this.ıslemler = ıslemler;
        this.imgIslemler = imgIslemler;
    }

    public String getIslemler() {
        return ıslemler;
    }

    public void setIslemler(String ıslemler) {
        this.ıslemler = ıslemler;
    }

    public int getImgIslemler() {
        return imgIslemler;
    }

    public void setImgIslemler(int imgIslemler) {
        this.imgIslemler = imgIslemler;
    }
}
