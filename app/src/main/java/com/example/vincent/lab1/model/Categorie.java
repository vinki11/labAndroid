package com.example.vincent.lab1.model;

import java.util.ArrayList;

/**
 * Created by Vincent on 2016-09-20.
 */

public class Categorie {

    private String Nom;
    private ArrayList<Poi> Items;

    public String getNom() {
        return Nom;
    }

    public void setNom(String pNom) {
        this.Nom = pNom;
    }

    public ArrayList<Poi> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Poi> pItems) {
        this.Items = pItems;
    }

}

