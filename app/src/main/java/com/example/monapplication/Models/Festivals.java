package com.example.monapplication.Models;

import java.util.ArrayList;

public class Festivals {
    private int id;
    private String nom;
    private ArrayList<Concours> listeConcours;

    public Festivals(int wid, String wnom)
    {
        id = wid;
        nom = wnom;
        listeConcours = new ArrayList<Concours>();
    }
    public Festivals(String wnom)
    {
        nom = wnom;
        listeConcours = new ArrayList<Concours>();
    }

    public Festivals() { }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String wNom) {
        nom = wNom;
    }
    public void setId(int wId) { id = wId;}

    @Override
    public String toString()
    {
        return nom;
    }
}
