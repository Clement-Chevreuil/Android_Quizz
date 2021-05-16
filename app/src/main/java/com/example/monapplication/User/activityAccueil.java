package com.example.monapplication.User;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.Admin.activityAccueilAdmin;
import com.example.monapplication.R;
import com.example.monapplication.UserAdapter.ConcoursAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import java.util.ArrayList;
import java.util.Date;

public class activityAccueil extends AppCompatActivity  implements View.OnClickListener{

    private LinearLayout myLayout;
    private ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBouton; //bouton sur leqeul on va associe le click
    private Button laDeco; //bouton sur leqeul on va associe le click
    private int idUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        leBouton = this.findViewById(R.id.btn);
        leBouton.setOnClickListener(this);

        laDeco = this.findViewById(R.id.btnDeconnexion);
        laDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ensemble = new ensemble();
        ensemble.creationBdd_festival(getApplicationContext()); //remplissage de ensemble
        ensemble.creationBdd_concours(getApplicationContext());
        ensemble.creationBdd_participe(getApplicationContext());

        idUtilisateur = this.getIntent().getExtras().getInt("idUtilisateur");
        int idTypeUtilisateur = this.getIntent().getExtras().getInt("idTypeUtilisateur");

        //gere le tableau avec les concours
        ArrayList<Concours>  lesConcours = ensemble.getLesConcoursEnCours(idUtilisateur);
        ListView ConcoursListView = findViewById(R.id.shop_list_view);
        ConcoursListView.setAdapter(new ConcoursAdapter(this, lesConcours, idUtilisateur));

        //ajout dynamique d'un bouton si c'est un administrateur (bouton permettant de gerer les vues
        if(idTypeUtilisateur == 1)
        {
            this.myLayout = findViewById(R.id.changeMode);
            Button changeMode = new Button(this);
            changeMode.setText("Vue Admin");

            final float scale = getResources().getDisplayMetrics().density;
            int dpWidthInPx  = (int) (200 * scale);
            int dpHeightInPx = (int) (40 * scale);
            changeMode.setHeight(dpHeightInPx);
            changeMode.setWidth(dpWidthInPx);
            int red = Color.parseColor("#BCFFE6E6");
            changeMode.setBackgroundColor(red);
            changeMode.setOnClickListener(this);
            
            changeMode.setId(R.id.bj);
            myLayout.addView(changeMode);
        }
    }

    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn:
                Intent unIntent = new Intent(this, activityScore.class);
                unIntent.putExtra("idUtilisateur", idUtilisateur);
                this.startActivity(unIntent);
                break;
            case R.id.bj:
                int idTypeUtilisateur = this.getIntent().getExtras().getInt("idTypeUtilisateur");
                Intent deuxIntent = new Intent(this, activityAccueilAdmin.class);
                deuxIntent.putExtra("idUtilisateur", idUtilisateur);
                deuxIntent.putExtra("idTypeUtilisateur", idTypeUtilisateur);
                this.startActivity(deuxIntent);
                finish();
                break;

        }

    }
}
