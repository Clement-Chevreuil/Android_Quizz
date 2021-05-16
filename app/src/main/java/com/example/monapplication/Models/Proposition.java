package com.example.monapplication.Models;

import java.util.ArrayList;

public class Proposition {

    private int id;
    private String intitule;
    private ArrayList<Repondre>  listeReponse;

    public Proposition(int id, String intitule)
    {
        this.id= id;
        this.intitule =  intitule;
        listeReponse = new ArrayList<Repondre>();
    }
    public Proposition()
    {
        listeReponse = new ArrayList<Repondre>();
    }

    public int getId() { return id; }
    public String getIntitule() { return intitule; }
    public ArrayList<Repondre> getListeQuestions() { return listeReponse; }

    public void setId(int id) { this.id = id; }
    public void setIntitule(String intitule) { this.intitule = intitule; }
    public void setListeReponse(ArrayList<Repondre> listeRepons) { this.listeReponse = listeRepons; }
}
