package com.example.monapplication.Models;

import java.util.ArrayList;

public class Repondre {
    private Proposition uneProposition;
    private Questions uneQuestion;
    private Boolean reponse;

    public Repondre(Proposition uneProposition, Questions uneQuestion, Boolean reponse)
    {
        this.uneProposition = uneProposition;
        this.uneQuestion = uneQuestion;
        this.reponse = reponse;
    }
    public Repondre(Proposition uneProposition, Questions uneQuestion)
    {
        this.uneProposition = uneProposition;
        this.uneQuestion = uneQuestion;
    }
    public Repondre(Proposition uneProposition)
    {
        this.uneProposition = uneProposition;
    }
    public Repondre(Questions uneQuestion)
    {
        this.uneQuestion = uneQuestion;
    }
    public Repondre()
    {

    }


    public Proposition getUneProposition() { return uneProposition; }
    public Questions getUneQuestion() { return uneQuestion; }
    public Boolean getReponse() { return reponse; }

    public void setReponse(Boolean reponse) { this.reponse = reponse; }
    public void setUneProposition(Proposition uneProposition) { this.uneProposition = uneProposition; }
    public void setUneQuestion(Questions uneQuestion) { this.uneQuestion = uneQuestion; }
}
