package com.example.monapplication.Admin;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
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

public class activityStatistiques extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout myLayout;
    private com.example.monapplication.GestionBdd.ensemble ensemble; //retient l'esemble des utilisateur
    private Button leBoutonRetour;

    private ArrayList<Festivals> lesFestivals;
    private ProgressBar[] question;
    private TextView[] text;
    private HashMap<String, EditText[]> listeChoix = new HashMap<String, EditText[]>();

    private ArrayList<Questions> lesQuestions;
    int idConcour;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);
        ensemble = new ensemble();
        idConcour = this.getIntent().getExtras().getInt("idConcour");
        ensemble.creationBdd_concours(this);

        lesQuestions = ensemble.getLesQuestionStats(1);

        this.myLayout = findViewById(R.id.myDynamicLayout);
        Log.e("tag", String.valueOf(lesQuestions.size()));

        question = new ProgressBar[lesQuestions.size() + 1];
        for(int i = 1; i < lesQuestions.size() + 1; i++)
        {
            question[i] = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
            question[i].setMax(100);
            question[i].setProgress(50);

            myLayout.addView(question[i]);
        }

        leBoutonRetour = this.findViewById(R.id.retour);
        leBoutonRetour.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.retour:
                finish();
                break;
        }
    }

}

