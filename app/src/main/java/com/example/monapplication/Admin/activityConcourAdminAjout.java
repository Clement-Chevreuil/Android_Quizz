package com.example.monapplication.Admin;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monapplication.Models.Proposition;
import com.example.monapplication.Models.Repondre;
import com.example.monapplication.R;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import com.example.monapplication.Models.Festivals;
import com.example.monapplication.Models.Questions;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;

public class activityConcourAdminAjout extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout myLayout;
    private ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBoutonRetour;
    private Button leBoutonAjout;
    private Spinner spinner;
    private ArrayList<Festivals> lesFestivals;

    private RadioGroup[] Choix;
    private EditText[] question;
    private HashMap<String, EditText[]> listeChoix = new HashMap<String, EditText[]>();

    private Festivals unFestival;
    private int nbQuestion;
    private int nbReponse;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concour_ajout);

        nbQuestion = this.getIntent().getExtras().getInt("nbQuestion");
        nbReponse = this.getIntent().getExtras().getInt("nbReponse");

        this.myLayout = findViewById(R.id.myDynamicLayout);
        Choix = new RadioGroup[nbQuestion + 1];
        question = new EditText[nbQuestion + 1];

        int id[] = new int[nbReponse + 1];
        String names[] = new String[nbReponse + 1];

        for (int i=1; i < nbReponse + 1; i++)
        {
            id[i] = i;
            names[i] = "Reponse "+i;
            EditText[] choix = new EditText[nbQuestion + 1];
            String nom = "choix"+i;
            listeChoix.put(nom, choix);
        }

        for(int i = 1; i < nbQuestion + 1; i++)
        {
            TextView Reponse = new TextView(this);
            TextView Question = new TextView(this);
            question[i] = new EditText(this);
            for (int k=1; k < nbReponse + 1; k++)
            { listeChoix.get("choix"+k)[i] = new EditText((this)); }
            Choix[i] = new RadioGroup(this);
            Choix[i].setOrientation(RadioGroup.VERTICAL);

// affectation d’un id aux boutons radio afin de savoir lequel est selectionné

            for(int l=0; l<names.length; l++)
            {
                if(l >= 1)
                {
                    RadioButton v = new RadioButton(this);
                    v.setId(id[l]);
                    v.setText(names[l]);
                    Choix[i].addView(v);
                }

            }
            myLayout.addView(Question);
            myLayout.addView(question[i]);
            Question.setText("Question :" + i);
            Reponse.setText("Bonne réponse :");
            for (int k=1; k < nbReponse + 1; k++)
            {
                listeChoix.get("choix"+k)[i].setHint("Reponse " + k);
                myLayout.addView(listeChoix.get("choix"+k)[i]);
            }
            question[i].setHint("Question:");

            myLayout.addView(Reponse);
            myLayout.addView(Choix[i]);
        }


        ensemble = new ensemble();
        ensemble.creationBdd_concours(this);
        lesFestivals = ensemble.getLesFestivals();

        spinner = this.findViewById(R.id.spinner);

        ArrayAdapter<Festivals> arrayAdapter = new ArrayAdapter<Festivals>(this, android.R.layout.simple_spinner_item, lesFestivals);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unFestival = (Festivals) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        leBoutonRetour = this.findViewById(R.id.retour);
        leBoutonRetour.setOnClickListener(this);

        leBoutonAjout = this.findViewById(R.id.valider);
        leBoutonAjout.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.retour:
                Intent unIntent = new Intent(this, activityConcoursAdmin.class);
                this.startActivity(unIntent);
                finish();
                break;

            case R.id.valider :
                int[] choix = new int[nbReponse + 1];
                String[] choixAdmin = new String[nbReponse + 1];

                EditText nomEdit = this.findViewById(R.id.nom);
                String nom = nomEdit.getText().toString();
                EditText nomEdite = this.findViewById(R.id.recompense);
                String recompence = nomEdite.getText().toString();

                DatePicker dateDebut = this.findViewById(R.id.date1);
                Date uneDateDebut = new Date(dateDebut.getYear()-1900, dateDebut.getMonth()-1, dateDebut.getDayOfMonth()-1);
                DatePicker dateFin = this.findViewById(R.id.date2);
                Date uneDateFin = new Date(dateFin.getYear()-1900, dateFin.getMonth()-1, dateFin.getDayOfMonth()-1);

                boolean reponseFinal = false;
                for (int k=1; k < nbReponse; k++)
                {
                    choix[k] = Choix[k].getCheckedRadioButtonId();
                    if(choix[k] >= 1 && choix[k] <= nbReponse) // a verif sert a si il est remplie ou pas
                    {
                        reponseFinal = true;
                        for(EditText[] unChoix : listeChoix.values())
                        {
                            choixAdmin[k] = unChoix[k].getText().toString();
                        }
                    }
                    else
                        {
                            reponseFinal = false;
                            break;
                        }
                }
                Log.e("reponsefinal", String.valueOf(reponseFinal));
                if (reponseFinal == true)
                {
                    boolean verifQuestion = false;
                    boolean verifProposition = false;
                    for (int r=1; r < nbQuestion +1 ; r++)
                    {
                        if(question[r].getText().toString() == "")
                        {
                          verifQuestion = false;
                        }
                        else
                        {
                            verifQuestion = true;
                        }
                    }
                    Log.e("verifQuestion", String.valueOf(verifQuestion));
                    for (EditText[] proposition : listeChoix.values())
                    {
                        for(int r=1; r < nbQuestion + 1; r++)
                        {
                            if(proposition[r].getText().toString() == "")
                            {
                                verifProposition = false;
                            }
                            else
                                {
                                    verifProposition = true;
                                }
                        }

                    }
                    Log.e("verifProposirion", String.valueOf(verifProposition));

                    if (recompence.isEmpty() || nom.isEmpty() ||verifQuestion == false || verifProposition == false )
                    {
                        Toast.makeText(this, "Veuiller remplir les champ correctement", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        int nouvelleId = ensemble.getDernierIdConcour() + 1;
                        int idQuestions = ensemble.getDernierIdQuestion();
                        int idProposition = ensemble.getDernierIdProposition();

                        Concours unConcour = new Concours(nouvelleId,nom, uneDateDebut, uneDateFin, recompence, unFestival);
                        ensemble.AjouterConcour(unConcour);
                        for(int i=1; i < nbQuestion + 1 ; i++)
                        {
                            int lol = Choix[i].getCheckedRadioButtonId();
                            idQuestions = idQuestions + 1;
                            Questions uneQuestion = new Questions();
                            uneQuestion.setId(idQuestions);
                            uneQuestion.setTitre(question[i].getText().toString());
                            uneQuestion.setUnConcour(unConcour);
                            ensemble.AjouterQuestion(uneQuestion);


                            for(int n=1; n < nbReponse + 1; n++)
                            {
                                EditText[] listeQuestions = listeChoix.get("choix"+n);// grosse triche
                                Log.e("tag", listeQuestions[i].getText().toString() );
                                idProposition = idProposition + 1;
                                Proposition uneProposition = new Proposition();
                                uneProposition.setId(idProposition);
                                uneProposition.setIntitule(listeQuestions[i].getText().toString());
                                ensemble.AjouterProposition(uneProposition);

                                Repondre uneReponse = new Repondre();
                                uneReponse.setUneQuestion(uneQuestion);
                                uneReponse.setUneProposition(uneProposition);
                                if(lol == n)
                                { uneReponse.setReponse(true);}
                                else
                                { uneReponse.setReponse(false);}
                                ensemble.AjouterReponse(uneReponse);
                            }
                        }

                        Toast.makeText(this, "Inscription reussi", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent deuxIntent = new Intent(this, activityConcoursAdmin.class);
                        this.startActivity(deuxIntent);
                    }
                }
                else
                {
                   Toast.makeText(this, "Veuillez remplir correctement", Toast.LENGTH_SHORT).show();
                }
                break;
                }
    }

}
