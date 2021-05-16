package com.example.monapplication.Models;

import java.util.ArrayList;
import java.util.Date;

public class Utilisateurs {
    private int id;
    private String pseudo;
    private String motDePasse;
    private String prenom;
    private String nom;
    private Date dateNaiss;
    private String pays;
    private String ville;
    private String adresse;
    private String codePostal;
    private String mail;
    private String telephone;
    private TypeUtilisateur type;

    private ArrayList<Choix> listeChoix;
    private ArrayList<Participe> listeParticipation;

    public Utilisateurs(int wid, String wpseudo, String wmotPasse, String nom, String prenom, Date dateNaiss, String pays, String ville, String adresse, String codePostal, String mail, String telephone, TypeUtilisateur type) {
        id = wid;
        pseudo = wpseudo;
        motDePasse = wmotPasse;
        this.nom = nom;
        this.prenom= prenom;
        this.dateNaiss = dateNaiss;
        this.pays = pays;
        this.ville = ville;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.mail = mail;
        this.telephone = telephone;
        this.type = type;
        listeChoix = new ArrayList<Choix>();
        listeParticipation = new ArrayList<Participe>();
    }

    public Utilisateurs(String wpseudo, String wmotPasse, String nom, String prenom, Date dateNaiss, String pays, String ville, String adresse, String codePostal, String mail, String telephone, TypeUtilisateur type) {
        pseudo = wpseudo;
        motDePasse = wmotPasse;
        this.nom = nom;
        this.prenom= prenom;
        this.dateNaiss = dateNaiss;
        this.pays = pays;
        this.ville = ville;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.mail = mail;
        this.telephone = telephone;
        this.type = type;
        listeChoix = new ArrayList<Choix>();
        listeParticipation = new ArrayList<Participe>();
    }

    public Utilisateurs(int wid, String wpseudo, String wmotPasse, TypeUtilisateur type) {
        id = wid;
        pseudo = wpseudo;
        motDePasse = wmotPasse;
        listeChoix = new ArrayList<Choix>();
        this.type = type;
        listeParticipation = new ArrayList<Participe>();
    }

    public Utilisateurs(String wpseudo, String wmotPasse, TypeUtilisateur type) {
        pseudo = wpseudo;
        motDePasse = wmotPasse;
        listeChoix = new ArrayList<Choix>();
        this.type = type;
        listeParticipation = new ArrayList<Participe>();
    }
    public Utilisateurs(int wid) {
        id = wid;
        listeChoix = new ArrayList<Choix>();
        listeParticipation = new ArrayList<Participe>();
    }

    public Utilisateurs() {}

    public int getId() {
        return id;
    }
    public String getPseudo() {
        return pseudo;
    }
    public String getMotDePasse() { return motDePasse; }
    public String getPrenom() {
        return prenom;
    }
    public String getNom() {
        return nom;
    }
    public String getPays() {
        return pays;
    }
    public String getVille() {
        return ville;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getMail() { return mail; }
    public Date getDateNaiss(){return dateNaiss;}
    public TypeUtilisateur getType() { return type; }
    public ArrayList<Choix> getListeChoix() { return listeChoix; }
    public ArrayList<Participe> getListeParticipation() { return listeParticipation; }

    public void setType(TypeUtilisateur type) { this.type = type; }
    public void setPseudo(String wPseudo) { pseudo = wPseudo; }
    public void setId(int wId) { id = wId; }
    public void setNom(String wNom) { nom = wNom; }
    public void setPrenom(String wPrenom) { prenom = wPrenom; }
    public void setPays(String wPays) { pays = wPays; }
    public void setVille(String wVille) { ville = wVille; }
    public void setAdresse(String wAdresse) { adresse = wAdresse; }
    public void setCodePostal(String wCode) { codePostal = wCode; }
    public void setMotDePasse(String wmotPasse) { motDePasse = wmotPasse; }
    public void setMail(String wm) { mail = wm; }
    public void setTelephone(String wtelephone) { telephone = wtelephone; }
    public void setDateNaiss(Date dateNaiss) { this.dateNaiss = dateNaiss; }

    public void setListeChoix(ArrayList<Choix> listeChoix) { this.listeChoix = listeChoix; }
    public void setListeParticipation(ArrayList<Participe> listeParticipation) { this.listeParticipation = listeParticipation; }
}
