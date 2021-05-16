package com.example.monapplication.Models;

import java.sql.Time;
import java.util.Date;

public class Participe {

    private Concours unConcour;
    private Utilisateurs unUtilisateur;
    private int score;
    private Date depart;
    private Date fin;

    public Participe(Concours unConcour, Utilisateurs unUtilisateur, Date depart, Date fin, int score)
    {
        this.unConcour = unConcour;
        this.unUtilisateur = unUtilisateur;
        this.depart = depart;
        this.fin = fin;
        this.score = score;

    }
    public Participe(Concours unConcour, Utilisateurs unUtilisateur, Date fin, int score)
    {
        this.unConcour = unConcour;
        this.unUtilisateur = unUtilisateur;
        this.fin = fin;
        this.score = score;

    }
    public Participe(Concours unConcour, Utilisateurs unUtilisateur, Date depart)
    {
        this.unConcour = unConcour;
        this.unUtilisateur = unUtilisateur;
        this.depart = depart;
    }

    public Participe() { }

    public Concours getUnConcour(){return unConcour;}
    public Utilisateurs getUnUtilisateur(){return unUtilisateur;}
    public int getScore(){return score;}
    public Date getDepart(){return depart;}
    public Date getFin(){return fin;}

    public Date getTemps()
    {
       // int heure = this.getFin().getHours() - this.getDepart().getHours();
        //int minute = this.getFin().getMinutes() - this.getDepart().getMinutes();
        //int second = this.getFin().getSeconds() - this.getDepart().getSeconds();
        //Time tonTemps = new Time(heure, minute, second);
        //return tonTemps;

        long temps = this.getFin().getTime() - getDepart().getTime();
        Date tonTemps = new Date(temps);
        return tonTemps;
    }

    public void setScore(int wScore){score = wScore;}
    public void setUnUtilisateur(Utilisateurs wUtilisateur){unUtilisateur = wUtilisateur;}
    public void setUnConcour(Concours wConcour){unConcour = wConcour;}
    public void setDepart(Date wDepart){depart = wDepart;}
    public void setFin(Date wFin){fin= wFin;}


}
