package com.example.monapplication.Admin;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.AdminAdapter.ConcoursFiniAdminAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import java.util.ArrayList;

public class activityConcoursFini extends AppCompatActivity implements View.OnClickListener{
    private ensemble ensembleClassement; //retient l'esemble des utilisateur
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concour_resultat);

        retour = findViewById(R.id.btnRetour);
        retour.setOnClickListener(this);

        ensembleClassement = new ensemble();
        ensembleClassement.creationBdd_participe(getApplicationContext());

        ArrayList<Concours> lesConcoursRetenu = new ArrayList<Concours>();
        lesConcoursRetenu = ensembleClassement.getLesConcoursFini();

        ListView ConcoursListView = findViewById(R.id.shop_list_view);
        ConcoursListView.setAdapter(new ConcoursFiniAdminAdapter(this, lesConcoursRetenu));
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRetour:
                finish();
                break;
        }
    }

}
