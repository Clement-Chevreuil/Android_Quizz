package com.example.monapplication.Models;

import java.util.ArrayList;

public class TypeUtilisateur {
    private int id;
    private String libelle;
    ArrayList<Utilisateurs> ListUtilisateurs;

    public TypeUtilisateur(int id, String libelle)
    {
        this.id = id;
        this.libelle = libelle;
        ListUtilisateurs = new ArrayList<>();
    }
    public TypeUtilisateur(int id)
    {
        this.id = id;
        ListUtilisateurs = new ArrayList<>();
    }
    public TypeUtilisateur(){}

    public String getLibelle() { return libelle; }
    public int getId() { return id; }

    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setId(int id) { this.id = id; }
}
