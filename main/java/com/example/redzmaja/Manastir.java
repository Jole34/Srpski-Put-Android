package com.example.redzmaja;

import android.widget.ImageView;

public class Manastir {

    private String slika;
    private String godina;
    private String naziv;


    public Manastir(String slika, String godina, String naziv) {
        this.slika = slika;
        this.godina = godina;
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public String getGodina() {
        return godina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}


