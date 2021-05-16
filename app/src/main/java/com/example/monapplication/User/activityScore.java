package com.example.monapplication.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.UserAdapter.ScoreConcoursAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Participe;
import java.util.ArrayList;
import java.util.List;

public class activityScore extends AppCompatActivity implements View.OnClickListener {

    private ensemble ensembleFestival; //retient l'esemble des utilisateur
    private Button retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concour_resultat);

        int idUtilisateur = this.getIntent().getExtras().getInt("idUtilisateur");
        ensembleFestival = new ensemble();
        ensembleFestival.creationBdd_participe(getApplicationContext());

        retour=(Button)this.findViewById(R.id.btnRetour);
        retour.setOnClickListener(this);

        List<Participe> lesParticipation = new ArrayList<>();
        lesParticipation = ensembleFestival.getLesParticipationUtilisateur(idUtilisateur);
        ListView ConcoursListView = findViewById(R.id.shop_list_view);
        ConcoursListView.setAdapter(new ScoreConcoursAdapter(this, lesParticipation));

    }
    public void onClick(View v)
    {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

}

