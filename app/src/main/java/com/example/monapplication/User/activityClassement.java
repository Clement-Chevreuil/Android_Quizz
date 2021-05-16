package com.example.monapplication.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.monapplication.R;
import com.example.monapplication.UserAdapter.ClassementAdapter;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Participe;
import java.util.ArrayList;

public class activityClassement extends AppCompatActivity implements View.OnClickListener{

    private ensemble ensembleClassement; //retient l'esemble des utilisateur
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        ensembleClassement = new ensemble();
        ensembleClassement.creationBdd_participe(getApplicationContext());

        retour=(Button)this.findViewById(R.id.btnRetour);
        retour.setOnClickListener(this);

        int idConcour = this.getIntent().getExtras().getInt("idConcour");
        ArrayList<Participe> lesParticipationClassementTrie = new ArrayList<>();
        lesParticipationClassementTrie = ensembleClassement.getLesClassementTrie(idConcour);

        ListView ConcoursListView = findViewById(R.id.shop_list_view);
        ConcoursListView.setAdapter(new ClassementAdapter(this, lesParticipationClassementTrie));

    }
    public void onClick(View v)
    {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

}
