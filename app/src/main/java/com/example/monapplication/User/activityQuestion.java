package com.example.monapplication.User;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.monapplication.R;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Choix;
import com.example.monapplication.Models.Concours;
import com.example.monapplication.Models.Participe;
import com.example.monapplication.Models.Questions;
import com.example.monapplication.Models.Utilisateurs;
import java.util.Date;

public class activityQuestion extends AppCompatActivity implements View.OnClickListener {
    private com.example.monapplication.GestionBdd.ensemble ensemble;
    private TextView uneQuestion;
    private RadioGroup ensQuestions;
    private RadioButton[] reponses;
    private int[] numBoutonRadio;
    private Button result;
    private int numQuestion = 0;
    private int NumQuestionRecup;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        int idConcourRecup = this.getIntent().getExtras().getInt("idConcour");
        int idUtilisateurRecup = this.getIntent().getExtras().getInt("idUtilisateur");
        Log.e("taga2", String.valueOf(idUtilisateurRecup));
        String ref = String.valueOf(idConcourRecup);

        ensemble = new ensemble();
        ensemble.creationBdd_concours2(getApplicationContext(), idConcourRecup);
        ensemble.test();

        //initialisation des contrôles
        uneQuestion=(TextView)this.findViewById(R.id.txt_question);
        uneQuestion.setMinWidth(400);
        ensQuestions = (RadioGroup)this.findViewById(R.id.rdbg_question);
        reponses = new RadioButton[3];
        // creation de boutons radio
        reponses[0] = (RadioButton)
                getLayoutInflater().inflate(R.layout.gestion_bouton_radio,null);
        reponses[0].setTextColor(Color.parseColor("#220759"));
        reponses[0].setMinWidth(400);
        reponses[1] = (RadioButton)
                getLayoutInflater().inflate(R.layout.gestion_bouton_radio,null);
        reponses[1].setTextColor(Color.parseColor("#220759"));
        reponses[1].setMinWidth(400);
        reponses[2] = (RadioButton)
                getLayoutInflater().inflate(R.layout.gestion_bouton_radio,null);
        reponses[2].setTextColor(Color.parseColor("#220759"));
        reponses[2].setMinWidth(400);
//generation d’un Id pour les boutons radio
        numBoutonRadio = new int[3];
        numBoutonRadio[0] = 1;
        numBoutonRadio[1] = 2;
        numBoutonRadio[2] = 3;
// affectation d’un id aux boutons radio afin de savoir lequel est selectionné
        reponses[0].setId(numBoutonRadio[0]);
        reponses[1].setId(numBoutonRadio[1]);
        reponses[2].setId(numBoutonRadio[2]);
        // gestion du bouton
        result=(Button)this.findViewById(R.id.cmd_reponse);


        result.setOnClickListener(this);
        //lancement des questions....
        rempQuestions(numQuestion);

    }
    private void rempQuestions(int unNumQ)
    {
          uneQuestion.setText(ensemble.getQuestion(unNumQ));
          reponses[0].setText(ensemble.getProposition(unNumQ,0));
          reponses[1].setText(ensemble.getProposition(unNumQ,1));
          reponses[2].setText(ensemble.getProposition(unNumQ,2));
          ensQuestions.addView(reponses[0]);
          ensQuestions.addView(reponses[1]);
          ensQuestions.addView(reponses[2]);
    }
    @Override
    public void onClick(View v)
    {

        int idConcourRecup = this.getIntent().getExtras().getInt("idConcour");
        int idUtilisateurRecup = this.getIntent().getExtras().getInt("idUtilisateur");

        int valeur = ensemble.getLesQuestion(idConcourRecup).size();
        int retour = ensQuestions.getCheckedRadioButtonId();
        if (retour == 1 || retour == 2 || retour == 3)
        {
            String retourFinal = reponses[retour-1].getText().toString();
            String bonneRep = ensemble.getReponse(NumQuestionRecup);
            int idQuestion = ensemble.getQuestionId(NumQuestionRecup);

            Utilisateurs unUtilisateur = new Utilisateurs(idUtilisateurRecup);
            Concours unConcour = new Concours(idConcourRecup);
            Questions uneQuestion = new Questions(idQuestion);

            if(bonneRep.equals(retourFinal))
            {
                Toast.makeText(this, "Réponse correcte", Toast.LENGTH_SHORT).show();
                Choix unChoix = new Choix(retourFinal, true, uneQuestion, unUtilisateur );
                ensemble.AjouterChoix(unChoix);
            }
            else
                {
                    Toast.makeText(this, "Réponse fausse, il fallait choisir : " + bonneRep, Toast.LENGTH_SHORT).show();
                    Choix unChoix = new Choix(retourFinal, false, uneQuestion, unUtilisateur );
                    ensemble.AjouterChoix(unChoix);
                }

            if(NumQuestionRecup < (valeur - 1))
            {
                ensQuestions.clearCheck();
                NumQuestionRecup++;
                ensQuestions.removeView(reponses[2]);
                ensQuestions.removeView(reponses[1]);
                ensQuestions.removeView(reponses[0]);
                rempQuestions(NumQuestionRecup);
            }
            else
                {
                    int score = ensemble.getNbReponseValide(idUtilisateurRecup, idConcourRecup);
                    Date uneDate = new Date();
                    Participe uneParticipation = new Participe(unConcour, unUtilisateur,uneDate,score);
                    ensemble.MettreAJourParticipe(uneParticipation);
                    Toast.makeText(this, "Tu as fini le Quizz", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                }
        }
        else
            {
            Toast.makeText(this, "Veuillez remplir correctement s'il vous plait", Toast.LENGTH_SHORT).show();
        }
    }
}
