package com.example.monapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monapplication.R;
import com.example.monapplication.User.activityAccueil;

public class activityGestionNbQuestionEtReponse extends AppCompatActivity implements View.OnClickListener
{

    private Button retour;
    private Button valider;
    private EditText nbQuestions;
    private EditText nbPropositions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_nb_question_reponse);

        retour = this.findViewById(R.id.Retour);
        retour.setOnClickListener(this);

        valider = this.findViewById(R.id.Valider);
        valider.setOnClickListener(this);

        nbQuestions= this.findViewById(R.id.nbQuestion);


        nbPropositions = this.findViewById(R.id.nbReponse);

    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Retour:
                finish();
                break;
            case R.id.Valider:
                int theNbQuestions = Integer.parseInt(nbQuestions.getText().toString());
                int theNbProppositions = Integer.parseInt(nbPropositions.getText().toString());
                if(theNbQuestions < 3 || theNbProppositions < 3)
                {
                    Toast toast = Toast.makeText(this, "Minimum 3 questions et 3 réponses", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                    {
                        Intent unIntent = new Intent(this, activityConcourAdminAjout.class);
                        unIntent.putExtra("nbQuestion", theNbQuestions);
                        unIntent.putExtra("nbReponse", theNbProppositions);
                        this.startActivity(unIntent);
                        finish();
                    }
        }
    }
}
