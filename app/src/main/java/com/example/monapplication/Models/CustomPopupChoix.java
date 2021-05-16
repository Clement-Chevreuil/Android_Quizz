package com.example.monapplication.Models;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.monapplication.R;
import com.example.monapplication.User.activityAccueil;
import com.example.monapplication.Admin.activityAccueilAdmin;

public class CustomPopupChoix extends Dialog {

    private String title;
    private String subTitle;
    private Button yesButton, noButton;
    private TextView titleView, subTitleView;
    private Context unContext;
    private int idUtilisateur;
    private int idTypeUtilisateur;

    public CustomPopupChoix(final Context unContext, Activity activity, final int idUtilisateur, final int idTypeUtilisateur)
    {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);
        setContentView(R.layout.my_popup_choix_option);
        this.title = "Choix";
        this.subTitle = "Choissisez votre Interface";
        this.yesButton = findViewById(R.id.admin);
        this.noButton = findViewById(R.id.utilisateur);
        this.titleView = findViewById(R.id.titre);
        this.subTitleView = findViewById(R.id.sousTitre);
        this.unContext = unContext;
        this.idUtilisateur = idUtilisateur;
        this.idTypeUtilisateur = idTypeUtilisateur;

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(unContext, activityAccueilAdmin.class);
                final int idUtilisateur2 = idUtilisateur;
                unIntent.putExtra("idUtilisateur", idUtilisateur2);

                final int idTypeUtilisateur2 = idTypeUtilisateur;
                unIntent.putExtra("idTypeUtilisateur", idTypeUtilisateur2);

                unContext.getApplicationContext().startActivity(unIntent);
                dismiss();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent = new Intent(unContext, activityAccueil.class);
                final int idUtilisateur2 = idUtilisateur;
                unIntent.putExtra("idUtilisateur", idUtilisateur2);

                final int idTypeUtilisateur2 = idTypeUtilisateur;
                unIntent.putExtra("idTypeUtilisateur", idTypeUtilisateur2);

                unContext.getApplicationContext().startActivity(unIntent);
                dismiss();
            }
        });
    }

    public Button getYesButton() { return yesButton; }
    public Button getNoButton() { return noButton; }

    public void setTitle(String title) { this.title = title; }
    public void setSubTitle(String subTitle) { this.subTitle = subTitle; }

    public void build()
    {
        show();
        titleView.setText(title);
        subTitleView.setText(subTitle);
    }

}
