package com.example.vincent.lab1.model;

/**
 * Created by Vincent on 2016-09-20.
 */
public class Poi {

    private String Nom;
    private double Lattitude, Longitude;

    public String getNom() {
        return Nom;
    }

    public void setNom(String pNom) {
        this.Nom = pNom;
    }

    public double getLattitude() {
        return Lattitude;
    }

    public void setLattitude(double pLattitude) {
        this.Lattitude = pLattitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double pLongitude) {
        this.Longitude = pLongitude;
    }
}
