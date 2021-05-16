package com.example.monapplication.Models;

public class Choix
{
    private int id;
    private String choix;
    private boolean valide;
    private Questions uneQuestion;
    private Utilisateurs unUtilisateur;
    private Choix unChoix;
    public Choix(int id, String choix, boolean valide, Questions uneQuestion, Utilisateurs unUtilisateur)
    {
        this.id = id;
        this.choix = choix;
        this.valide = valide;
        this.uneQuestion = uneQuestion;
        this.unUtilisateur = unUtilisateur;
    }

    public Choix(String choix, boolean valide, Questions uneQuestion, Utilisateurs unUtilisateur)
    {
        this.choix = choix;
        this.valide = valide;
        this.uneQuestion = uneQuestion;
        this.unUtilisateur = unUtilisateur;
    }

    public Choix(Choix unChoix)
    {
        this.unChoix=unChoix;
    }
    public Choix() {}

    public int getId(){return id;}
    public String getChoix(){return choix;}
    public boolean getValide(){return valide;}
    public Questions getUneQuestion(){return uneQuestion;}
    public Utilisateurs getUnUtilisateur(){return unUtilisateur;}

    public void setChoix(String choix) { this.choix = choix; }
    public void setId(int id) { this.id = id; }
    public void setValide(boolean valide) { this.valide = valide; }
    public void setUneQuestion(Questions uneQuestion) { this.uneQuestion = uneQuestion; }
    public void setUnUtilisateur(Utilisateurs unUtilisateur) { this.unUtilisateur = unUtilisateur; }
}
