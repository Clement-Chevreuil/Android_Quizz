package com.example.monapplication.Admin;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.User.activityAccueil;

public class activityAccueilAdmin extends AppCompatActivity implements View.OnClickListener {
    private com.example.monapplication.GestionBdd.ensemble ensemble; //retient l'esemble des utilisateur
    private Button festival;
    private Button concourEtquestion;
    private Button adherent;
    private Button classementConcourFini;
    private Button deconnexion;
    private Button changeMode;
    private Button statistique;
    private int idUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_admin);

        idUtilisateur = this.getIntent().getExtras().getInt("idUtilisateur");

        festival = this.findViewById(R.id.festival);
        concourEtquestion = this.findViewById(R.id.concourEtquestion);
        adherent = this.findViewById(R.id.adherent);
        classementConcourFini = this.findViewById(R.id.ClassementFin);
        deconnexion = this.findViewById(R.id.deconnexion);
        changeMode = this.findViewById(R.id.changeMode);
        statistique = this.findViewById(R.id.Statistique);

        festival.setOnClickListener(this);
        concourEtquestion.setOnClickListener(this);
        adherent.setOnClickListener(this);
        classementConcourFini.setOnClickListener(this);
        deconnexion.setOnClickListener(this);
        changeMode.setOnClickListener(this);
        statistique.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.festival:
                Intent unIntent = new Intent(this, activityFestivalAdmin.class);
                this.startActivity(unIntent);
                break;
            case R.id.concourEtquestion :
                Intent deuxIntent = new Intent(this, activityConcoursAdmin.class);
                this.startActivity(deuxIntent);
                break;
            case R.id.adherent:
                Intent troisIntent = new Intent(this, activityAdherentAdmin.class);
                this.startActivity(troisIntent);
                break;
            case R.id.ClassementFin:
                Intent quatreIntent = new Intent(this, activityConcoursFini.class);
                this.startActivity(quatreIntent);
                break;
            case R.id.deconnexion:
                finish();
                break;
            case R.id.changeMode:
                idUtilisateur = this.getIntent().getExtras().getInt("idUtilisateur");
                int idTypeUtilisateur = this.getIntent().getExtras().getInt("idTypeUtilisateur");
                Intent cinqIntent = new Intent(this, activityAccueil.class);
                cinqIntent.putExtra("idUtilisateur", idUtilisateur);
                cinqIntent.putExtra("idTypeUtilisateur", idTypeUtilisateur);
                this.startActivity(cinqIntent);
                finish();
                break;
            case R.id.Statistique:
                Intent sixIntent = new Intent(this, activityChoixConcoursStatistiques.class);
                this.startActivity(sixIntent);
                break;

        }

    }
}
