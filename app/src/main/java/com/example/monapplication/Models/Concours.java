package com.example.monapplication.Models;

import java.util.ArrayList;
import java.util.Date;

public class Concours {
    private int id;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private String recompence;
    private Festivals unFestival;

    private ArrayList<Questions> listeQuestions;
    private Concours concours; //inutile


    public Concours(String nom,Date dateDebut, Date dateFin, String recompence, Festivals unFestival) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.recompence = recompence;
        this.unFestival = unFestival;
        listeQuestions = new ArrayList<Questions>();
    }

    public Concours(int id, String nom,Date dateDebut, Date dateFin, String recompence, Festivals unFestival) {
        this.id = id;
        this.nom = nom;
        this.unFestival = unFestival;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.recompence = recompence;
        listeQuestions = new ArrayList<Questions>();
    }

    public Concours(int id, String nom,Date dateDebut, Date dateFin, String recompence) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.recompence = recompence;
        listeQuestions = new ArrayList<Questions>();
    }


    public Concours(int id, String nom) {
        this.id = id;
        this.nom = nom;
        listeQuestions = new ArrayList<Questions>();
    }
    public Concours(int id) {
        this.id = id;
        listeQuestions = new ArrayList<Questions>();
    }

    public Concours() {listeQuestions = new ArrayList<Questions>(); }

    public int getId() { return id; }
    public String getNom() {return nom;}
    public Date getDateDebut() {return dateDebut;}
    public Date getDateFin() {return dateFin;}
    public String getRecompence() {return recompence;}
    public Festivals getUnFestival(){return unFestival;}

    public ArrayList<Questions> getListeQuestions() { return listeQuestions; }

    public void setId(int wId) {
        id = wId;
    }
    public void setNom(String wNom) { nom = wNom; }
    public void setDateDebut(Date wDate) { dateDebut = wDate; }
    public void setDateFin(Date wDate) { dateFin = wDate; }
    public void setRecompence(String wRecompence) { recompence = wRecompence; }
    public void setUnFestival(Festivals wFestival){unFestival = wFestival;}

    public void setListeQuestions(ArrayList<Questions> listeQuestions) { this.listeQuestions = listeQuestions; }
}
