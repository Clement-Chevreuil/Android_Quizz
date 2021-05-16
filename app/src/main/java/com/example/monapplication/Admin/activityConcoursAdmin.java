package com.example.monapplication.Admin;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.AdminAdapter.ConcoursAdminAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import java.util.ArrayList;
import java.util.List;

public class activityConcoursAdmin extends AppCompatActivity implements View.OnClickListener
{
    private ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBoutonRetour;
    private Button leBoutonAjout;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concours_admin);

        context = this;
        ensemble = new ensemble();
        ensemble.creationBdd_festival(getApplicationContext()); //remplissage de ensemble
        ensemble.creationBdd_concours(getApplicationContext());
        ensemble.creationBdd_participe(getApplicationContext());

        List<Concours> lesConcours = new ArrayList<>();
        lesConcours = ensemble.getLesConcours();

        ListView ConcoursListView = findViewById(R.id.festival_admin_list_view);
        ConcoursListView.setAdapter(new ConcoursAdminAdapter(this, lesConcours));

        leBoutonRetour = this.findViewById(R.id.retour);
        leBoutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        leBoutonAjout = this.findViewById(R.id.ajout);
        leBoutonAjout.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Intent unIntent = new Intent(this, activityGestionNbQuestionEtReponse.class);
        this.startActivity(unIntent);
        finish();
    }

}
