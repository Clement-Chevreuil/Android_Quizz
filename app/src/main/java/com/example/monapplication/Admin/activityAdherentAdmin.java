package com.example.monapplication.Admin;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.AdminAdapter.AdherentAdminAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Utilisateurs;
import java.util.ArrayList;
import java.util.List;

public class activityAdherentAdmin extends AppCompatActivity
{
    private com.example.monapplication.GestionBdd.ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBoutonRetour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adherent_admin);

        ensemble = new ensemble();
        ensemble.creationBdd_festival(getApplicationContext()); //remplissage de ensemble
        ensemble.creationBdd_concours(getApplicationContext());
        ensemble.creationBdd_participe(getApplicationContext());

        List<Utilisateurs> lesAdherents = new ArrayList<Utilisateurs>();
        lesAdherents = ensemble.getLesUtilisateursAdmin();

        ListView FestivalListView = findViewById(R.id.adherent_admin_list_view);
        FestivalListView.setAdapter(new AdherentAdminAdapter(this, lesAdherents));

        leBoutonRetour = this.findViewById(R.id.retour);
        leBoutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

