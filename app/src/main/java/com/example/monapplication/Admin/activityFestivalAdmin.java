package com.example.monapplication.Admin;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.AdminAdapter.FestivalAdminAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Festivals;
import java.util.ArrayList;
import java.util.List;

public class activityFestivalAdmin extends AppCompatActivity implements View.OnClickListener
{
    private ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBoutonRetour;
    private Button leBoutonAjout;
    private EditText text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_admin);

        ensemble = new ensemble();
        ensemble.creationBdd_festival(getApplicationContext()); //remplissage de ensemble
        ensemble.creationBdd_concours(getApplicationContext());
        ensemble.creationBdd_participe(getApplicationContext());

        List<Festivals> lesFestivals = new ArrayList<>();
        lesFestivals = ensemble.getLesFestivals();

        ListView FestivalListView = findViewById(R.id.festival_admin_list_view);
        FestivalListView.setAdapter(new FestivalAdminAdapter(this, lesFestivals));

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
        text = this.findViewById(R.id.nomFestival);
        String leText = text.getText().toString();

        if (leText == "")
        {
            Toast.makeText(this, "Veuillez remplir le champ correctement", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Festivals unFestival = new Festivals(leText);
            ensemble.AjouterFestival(unFestival);
            Toast.makeText(this, "Festival ajouté.", Toast.LENGTH_SHORT).show();
            this.recreate();

        }

    }
}
