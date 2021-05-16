package com.example.monapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monapplication.User.activityAccueil;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.CustomPopupChoix;
import com.example.monapplication.Models.CustomPopupInscription;
import com.example.monapplication.Models.TypeUtilisateur;
import com.example.monapplication.Models.Utilisateurs;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Gestion avec la base de donnee
    private ensemble ensemble;

    //variable locale
    private String thePseudo;
    private String theMotPasse;

    //Element Graphique
    private TextView unUtilisateur;
    private EditText pseudo;
    private EditText motPasse;
    private Button leBouton;
    private Button leBoutonInscription;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;

        //Gestion de la BDD Si la bdd est vide ajoute les types d'utilisateur et des utilisateur (un admin et un utilisateur)
        ensemble = new ensemble();
        ensemble.creationBdd_typeUtilisateur(getApplicationContext());
        ensemble.creationBdd_utilisateur(getApplicationContext());
        ensemble.creationBdd_festival(getApplicationContext());

        //Connexion des elements à l'interface graphique
        leBouton = this.findViewById(R.id.btnConnexion);
        unUtilisateur = this.findViewById(R.id.txtPseudo);
        leBoutonInscription = this.findViewById(R.id.inscription);

        // Ajout d'un evenement onclick
        leBouton.setOnClickListener(this);

        //Generattion et gestion de la popup pour l'inscription (attention cette popup gerer via le model Custom Popup Inscription
        leBoutonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                final CustomPopupInscription popupInscription = new CustomPopupInscription(activity);
                popupInscription.setTitle("Inscription");
                popupInscription.setSubTitle("Veuillez renseigner les champs si dessous");


                //Gestion du bouton valider de la popup inscription
                popupInscription.getYesButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (popupInscription.getNom() == "" || popupInscription.getPrenom() == "" || popupInscription.getPseudo() == ""|| popupInscription.getPays() == "" || popupInscription.getVille() == "" ||popupInscription.getAdresse() == "" || popupInscription.getCodePostal() == "" || popupInscription.getMail() == "" || popupInscription.getTelephone() == "" || popupInscription.getMotDePasse() == "") {
                        Toast oui = Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs.", Toast.LENGTH_LONG);
                        oui.show();
                    }
                    else {
                        String pseudo = popupInscription.getPseudo();
                        String motDePasse = popupInscription.getMotDePasse();
                        String nom = popupInscription.getNom() ;
                        String prenom = popupInscription.getPrenom();
                        Date uneDate = popupInscription.getDateNaiss();
                        String pays = popupInscription.getPays();
                        String ville = popupInscription.getVille();
                        String adresse = popupInscription.getAdresse();
                        String codePostal = popupInscription.getCodePostal();
                        String mail = popupInscription.getMail() ;
                        String telephone = popupInscription.getTelephone();
                        TypeUtilisateur type = new TypeUtilisateur(2,"Utilisateur");

                        //verification si les elements de l'interface sont vides ou non.
                        if(pseudo.isEmpty() || motDePasse.isEmpty() || nom.isEmpty() || prenom.isEmpty() || pays.isEmpty() || ville.isEmpty() || adresse.isEmpty() || codePostal.isEmpty() || mail.isEmpty() ||telephone.isEmpty())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Remplissez les champs correctement!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else
                        {
                            Utilisateurs nouveauUtilisateur  = new Utilisateurs(pseudo, motDePasse, nom , prenom, uneDate, pays, ville, adresse, codePostal, mail, telephone, type);
                            ensemble.AjouterUtilisateur(nouveauUtilisateur);
                            Toast oui = Toast.makeText(getApplicationContext(), "Inscription reussi", Toast.LENGTH_LONG);
                            oui.show();
                            popupInscription.dismiss();
                        }
                    }
                }
                });

                //Gestion du bouton de retour pour fermer la popup
                popupInscription.getNoButton().setOnClickListener(new View.OnClickListener()
                {
                   @Override
                   public void onClick(View v) {
                       popupInscription.dismiss();
                   }
               });
                popupInscription.build();
            }
        });
    }

    //Gestion de la connexion
    @Override
    public void onClick(View v) {

        pseudo = findViewById(R.id.txtPseudo);
        thePseudo = pseudo.getText().toString();
        motPasse = findViewById(R.id.txtMotPasse);
        theMotPasse = motPasse.getText().toString();

        if (thePseudo.isEmpty() || theMotPasse.isEmpty())
        {
            Toast toast = Toast.makeText(this, "Remplisser les champs correctement!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            Utilisateurs lol = ensemble.getUtilisateur(thePseudo, theMotPasse);
            String mot;

            if (lol.getPseudo() == null)
            {
                mot = "Erreur pseudo ou mot de passe.";
                Toast toast = Toast.makeText(this, mot, Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                if(lol.getType().getId() == 2)
                {
                    pseudo.setText("");
                    motPasse.setText("");
                    mot = "Connexion Reussi";
                    Toast toast = Toast.makeText(this, mot, Toast.LENGTH_SHORT);
                    toast.show();
                    Intent unIntent = new Intent(this, activityAccueil.class);
                    unIntent.putExtra("idUtilisateur", lol.getId());
                    unIntent.putExtra("idTypeUtilisateur", lol.getType().getId());
                    this.startActivity(unIntent);
                }
                //Si c'est un administrateur ouvrir une popup (cf model custom popup choix) qui choisis sur qu'elle interface il veut aller
                else if (lol.getType().getId() == 1)
                {

                    final CustomPopupChoix popupInscription = new CustomPopupChoix(this, activity, lol.getId(), lol.getType().getId());
                    popupInscription.setTitle("Choix");
                    popupInscription.setSubTitle("Choissisez votre Interface");
                    popupInscription.build();
                    pseudo.setText("");
                    motPasse.setText("");
                }

            }
        }
    }
}
