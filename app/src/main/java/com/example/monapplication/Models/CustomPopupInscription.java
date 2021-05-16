package com.example.monapplication.Models;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.monapplication.R;

import java.util.Date;

public class CustomPopupInscription extends Dialog {

    private String title;
    private String subTitle;
    private Button yesButton, noButton;
    private TextView titleView, subTitleView;
    private EditText nom, prenom,pseudo, pays, ville, adresse, codePostal, telephone, mail, motDePasse;
    private DatePicker dateNaiss;

    public CustomPopupInscription(Activity activity)
    {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        setContentView(R.layout.my_popup_inscription);
        this.title = "Mon titre";
        this.subTitle = "mon sous titre";
        this.yesButton = findViewById(R.id.valider);
        this.noButton = findViewById(R.id.annuler);
        this.titleView = findViewById(R.id.titre);
        this.subTitleView = findViewById(R.id.sousTitre);
        this.nom = findViewById(R.id.nom);
        this.prenom = findViewById(R.id.prenom);
        this.dateNaiss = findViewById(R.id.dateNaiss);
        this.pays = findViewById(R.id.Pays);
        this.ville = findViewById(R.id.Ville);
        this.adresse = findViewById(R.id.Adresse);
        this.codePostal = findViewById(R.id.CodePostal);
        this.telephone = findViewById(R.id.Telephone);
        this.mail = findViewById(R.id.Mail);
        this.motDePasse = findViewById(R.id.MotDePasse);
        this.pseudo = findViewById(R.id.pseudo);

    }


    public Button getYesButton() { return yesButton; }
    public Button getNoButton() { return noButton; }
    public String getNom() { return nom.getText().toString(); }
    public String getPrenom() { return prenom.getText().toString(); }
    public Date getDateNaiss()
    {
        Date uneDate = new Date(dateNaiss.getYear()-1900, dateNaiss.getMonth()-1, dateNaiss.getDayOfMonth()-1);
        return uneDate;
    }
    public String getPays() { return pays.getText().toString(); }
    public String getVille() { return ville.getText().toString(); }
    public String getAdresse() { return adresse.getText().toString(); }
    public String getCodePostal() { return codePostal.getText().toString(); }
    public String getMail() { return mail.getText().toString(); }
    public String getTelephone() { return telephone.getText().toString(); }
    public String getMotDePasse() { return motDePasse.getText().toString(); }
    public String getPseudo() { return pseudo.getText().toString(); }

    public void setTitle(String title) { this.title = title; }
    public void setSubTitle(String subTitle) { this.subTitle = subTitle; }

    public void setNom(EditText nom) { this.nom = nom; }
    public void setPrenom(EditText prenom) { this.prenom = prenom; }
    public void setPays(EditText pays) { this.pays = pays; }
    public void setVille(EditText ville) { this.ville = ville; }
    public void setAdresse(EditText adresse) { this.adresse = adresse; }
    public void setCodePostal(EditText codePostal) { this.codePostal = codePostal; }
    public void setTelephone(EditText telephone) { this.telephone = telephone; }
    public void setMail(EditText mail) { this.mail = mail; }
    public void setMotDePasse(EditText motDePasse) { this.motDePasse = motDePasse; }
    public void setPseudo(EditText pseudo) { this.pseudo = pseudo; }

    public void build()
    {
        show();
        titleView.setText(title);
        subTitleView.setText(subTitle);
    }
}
