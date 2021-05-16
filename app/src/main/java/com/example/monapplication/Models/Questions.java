package com.example.monapplication.Models;
import java.util.ArrayList;

public class Questions
{
    private int id;
    private String titre;
    private Concours unConcour;
    private ArrayList<Choix> listeChoix;
    private ArrayList<Repondre> listeReponse;

    public Questions(int id, String titre, Concours unConcour)
    {
        this.id= id;
        this.titre =  titre;
        this.unConcour = unConcour;

        listeChoix = new ArrayList<Choix>();
        listeReponse = new ArrayList<Repondre>();
    }
    public Questions(int id)
    {
        this.id= id;
        listeChoix = new ArrayList<Choix>();
        listeReponse = new ArrayList<Repondre>();
    }
    public Questions(String titre, Concours unConcour)
    {
        this.titre =  titre;
        this.unConcour = unConcour;

        listeChoix = new ArrayList<Choix>();
        listeReponse = new ArrayList<Repondre>();
    }
    public Questions()
    {
        listeChoix = new ArrayList<Choix>();
        listeReponse = new ArrayList<Repondre>();
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public Concours getUnConcour(){return unConcour;}
    public ArrayList<Choix> getListeChoix() { return listeChoix; }
    public ArrayList<Repondre> getListeReponse() { return listeReponse; }

    public void ajoutRepondre (Repondre uneRep) { listeReponse.add(uneRep); }
    public void setId(int wId) { id = wId;}
    public void setTitre(String wIntitule) {titre = wIntitule;}
    public void setUnConcour(Concours wConcour){unConcour = wConcour;}
    public void setListeReponse(ArrayList<Repondre> listeReponse) { this.listeReponse = listeReponse; }
    public void setListeChoix(ArrayList<Choix> listeChoix) { this.listeChoix = listeChoix; }
}
