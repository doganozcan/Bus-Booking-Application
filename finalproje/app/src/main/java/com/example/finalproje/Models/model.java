package com.example.finalproje.Models;

public class model {
    String id;
    Boolean durum;
    Integer koltukNo;
    String seferid;

    public String getSeferid() {
        return seferid;
    }

    public void setSeferid(String seferid) {
        this.seferid = seferid;
    }


    public model( int koltukNo, Boolean durum,String seferid) {

        this.koltukNo = koltukNo;
        this.durum = durum;
        this.seferid=seferid;
    }

    public model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(Integer koltukNo) {
        this.koltukNo = koltukNo;
    }

    public Boolean getDurum() {

        return durum;
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
    }
}
