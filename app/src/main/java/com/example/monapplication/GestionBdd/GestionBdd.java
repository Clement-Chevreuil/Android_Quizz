package com.example.monapplication.GestionBdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.monapplication.Models.Choix;
import com.example.monapplication.Models.Concours;
import com.example.monapplication.Models.Festivals;
import com.example.monapplication.Models.Participe;
import com.example.monapplication.Models.Proposition;
import com.example.monapplication.Models.Questions;
import com.example.monapplication.Models.Repondre;
import com.example.monapplication.Models.TypeUtilisateur;
import com.example.monapplication.Models.Utilisateurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionBdd extends SQLiteOpenHelper {
    // gestion des champs avec des constantes
    private static final String nomTableUtilisateurs = "Utilisateurs";
    private static final String nomTableFestivals = "Festivals";
    private static final String nomTableConcours = "Concours";
    private static final String nomTableQuestions = "Questions";
    private static final String nomTableParticipe = "Participe";
    private static final String nomTableChoix = "Choix";
    private static final String nomTableTypeUtilisateur = "Type_utilisateur";
    private static final String nomTableProposition = "Proposition";
    private static final String nomTableRepondre = "Repondre";

    private static final String idUtilisateur = "id_utilisateur";
    private static final String idFestival = "id_festival";
    private static final String idConcour = "id_concour";
    private static final String idQuestion = "id_question";
    private static final String idTypeUtilisateur = "id_type_utilisateur";
    private static final String idProposition = "id_proposition";

    private static final String intitule = "intitule";
    private static final String reponse = "reponse";

    private static final String idChoix = "id_choix";
    private static final String choixUtilisateur = "choix_utilisateur";
    private static final String valide = "valide";

    private static final String libelle = "libelle";
    private static final String nom = "nom";
    private static final String prenom = "prenom";
    private static final String pays = "pays";
    private static final String ville = "ville";
    private static final String adresse = "adresse";
    private static final String codePostal = "code_postal";
    private static final String mail = "mail";
    private static final String telephone = "telephone";
    private static final String dateDeNaissance = "date_de_naissance";

    private static final String nomConcour = "nom_concour";
    private static final String nomFestival = "nom_festival";

    private static final String pseudo = "pseudo";
    private static final String motDePasse = "mot_de_passe";

    private static final String titre = "titre";
    private static final String score = "score";

    private static final String boolReponse = "bool_reponse";

    private static final String dateDebutConcour = "date_debut_concour";
    private static final String dateFinConcour = "date_fin_concour";
    private static final String recompence = "recompence";

    private static final String departParticipation = "depart_participation";
    private static final String finParticipation = "fin_participation";

    private static final String idConcourFk = "id_concour_fk"; //clé etrangere pour Question
    private static final String idUtilisateurFk = "id_utilisateur_fk"; //clé etrangere pour Question
    private static final String idFestivalFk = "id_festival_fk"; //clé etrangere pour concours
    private static final String idQuestionFk = "id_question_fk"; //clé etrangere pour concours
    private static final String idTypeUtilisateurFk = "id_type_utilisateur_fk"; //clé etrangere pour concours
    private static final String idPropositionFk = "id_proposition_fk"; //clé etrangere pour concours

    // gestion de la requête de création
    private static final String reqCreationUtilisateurs = "CREATE TABLE " + nomTableUtilisateurs + " (" + idUtilisateur + " INTEGER PRIMARY KEY AUTOINCREMENT," + pseudo + " TEXT," + motDePasse+  " TEXT,"+ nom + " TEXT," + prenom  + " TEXT," + dateDeNaissance+ " TEXT," + pays + " TEXT," + ville + " TEXT,"+ adresse + " TEXT,"+ codePostal +" TEXT," + mail + " TEXT," + telephone + " TEXT," + idTypeUtilisateurFk + " INTEGER);";
    private static final String reqCreationFestivals = "CREATE TABLE " + nomTableFestivals + " (" + idFestival + " INTEGER PRIMARY KEY AUTOINCREMENT," + nomFestival+ " TEXT);";
    private static final String reqCreationConcours= "CREATE TABLE " + nomTableConcours + " (" + idConcour + " INTEGER PRIMARY KEY AUTOINCREMENT," + nomConcour + " TEXT, " +dateDebutConcour+ " TEXT," + dateFinConcour + " TEXT," + recompence +" TEXT," + idFestivalFk  + " INTEGER, FOREIGN KEY("+ idFestivalFk +") REFERENCES Festivals ("+ idFestival +") ON DELETE CASCADE);";
    private static final String reqCreationQuestion ="CREATE TABLE "+ nomTableQuestions + " (" + idQuestion + " INTEGER PRIMARY KEY AUTOINCREMENT," + titre + " TEXT," + idConcourFk + " INTEGER, FOREIGN KEY("+ idConcourFk +") REFERENCES Concours ("+ idConcour +"));";
    private static final String reqCreationProposition ="CREATE TABLE "+ nomTableProposition + " (" + idProposition + " INTEGER PRIMARY KEY AUTOINCREMENT," + intitule + " TEXT);";
    private static final String reqCreationChoix = "CREATE TABLE " + nomTableChoix + " (" +idChoix+ " INTEGER PRIMARY KEY AUTOINCREMENT," +choixUtilisateur+ " TEXT," + valide +" TEXT,"+idUtilisateurFk+" INTEGER,"+ idQuestionFk +" INTEGER);";
    private static final String reqCreationTypeUtilisateur = "CREATE TABLE " + nomTableTypeUtilisateur + " (" +idTypeUtilisateur+ " INTEGER PRIMARY KEY AUTOINCREMENT," +libelle+ " TEXT);";

    private static final String reqCreationParticipe= "CREATE TABLE " + nomTableParticipe + " (" + score + " INTEGER NULL," + idUtilisateurFk + " INTEGER , " + idConcourFk  + " INTEGER, "+departParticipation+" TEXT,"+ finParticipation+" TEXT, PRIMARY KEY(" +idUtilisateurFk+", "+idConcourFk+"));";
    private static final String reqCreationRepondre = "CREATE TABLE " + nomTableRepondre + " (" + idQuestionFk + " INTEGER," + idPropositionFk + " INTEGER," + boolReponse + " TEXT, PRIMARY KEY(" + idPropositionFk+", " + idQuestionFk +"), FOREIGN KEY("+ idQuestionFk +") REFERENCES Questions("+ idQuestion +"), FOREIGN KEY("+ idPropositionFk +") REFERENCES Proposition("+ idProposition +"));";

    private static String DB_NAME = "Bdd_Utilisateur.db";
    private static int DB_VERSION = 1;

    public GestionBdd(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("test base", "insertion " + reqCreationProposition);
        Log.i("test base", "insertion " + reqCreationUtilisateurs);
        Log.i("test base", "insertion " + reqCreationFestivals);
        Log.i("test base", "insertion " + reqCreationQuestion);
        Log.i("test base", "insertion " + reqCreationConcours);
        Log.i("test base", "insertion " + reqCreationParticipe);
        Log.i("test base", "insertion " + reqCreationChoix);
        Log.i("test base", "insertion " + reqCreationTypeUtilisateur);
        Log.i("test base", "insertion " + reqCreationRepondre);
        db.execSQL(reqCreationUtilisateurs);
        db.execSQL(reqCreationFestivals);
        db.execSQL(reqCreationQuestion);
        db.execSQL(reqCreationConcours);
        db.execSQL(reqCreationParticipe);
        db.execSQL(reqCreationChoix);
        db.execSQL(reqCreationTypeUtilisateur);
        db.execSQL(reqCreationProposition);
        db.execSQL(reqCreationRepondre);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String reqSupp = "DROP TABLE IF EXISTS " + nomTableUtilisateurs + reqCreationUtilisateurs;
        db.execSQL(reqSupp);
        String reqSupp2 = "DROP TABLE IF EXISTS " + nomTableFestivals + reqCreationFestivals;
        db.execSQL(reqSupp2);
        String reqSupp3 = "DROP TABLE IF EXISTS " + nomTableQuestions + reqCreationQuestion;
        db.execSQL(reqSupp3);
        String reqSupp4 = "DROP TABLE IF EXISTS " + nomTableConcours + reqCreationConcours;
        db.execSQL(reqSupp4);
        String reqSupp6 = "DROP TABLE IF EXISTS " + nomTableChoix + reqCreationChoix;
        db.execSQL(reqSupp6);
        String reqSupp7 = "DROP TABLE IF EXISTS " + nomTableTypeUtilisateur + reqCreationTypeUtilisateur;
        db.execSQL(reqSupp7);
        String reqSupp8 = "DROP TABLE IF EXISTS " + nomTableProposition + reqCreationProposition;
        db.execSQL(reqSupp8);
        String reqSupp9 = "DROP TABLE IF EXISTS " + nomTableRepondre + reqCreationRepondre;
        db.execSQL(reqSupp9);
        String reqSupp5 = "DROP TABLE IF EXISTS " + nomTableParticipe + reqCreationParticipe;
        db.execSQL(reqSupp5);


        onCreate(db);
    }

    public Long AjouterUtilisateur(Utilisateurs unUtilisateurs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(pseudo, unUtilisateurs.getPseudo());
        values.put(motDePasse, unUtilisateurs.getMotDePasse());
        values.put(prenom, unUtilisateurs.getPrenom());
        values.put(dateDeNaissance, unUtilisateurs.getDateNaiss().getTime());
        values.put(nom, unUtilisateurs.getNom());
        values.put(pays, unUtilisateurs.getPays());
        values.put(ville, unUtilisateurs.getVille());
        values.put(adresse, unUtilisateurs.getAdresse());
        values.put(codePostal, unUtilisateurs.getCodePostal());
        values.put(mail, unUtilisateurs.getMail());
        values.put(telephone, unUtilisateurs.getTelephone());
        values.put(idTypeUtilisateurFk, unUtilisateurs.getType().getId());
        Long insertion = db.insert(nomTableUtilisateurs, null, values);
        return insertion;
    }

    public void AjouterFestival(Festivals unFestival) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(nomFestival, unFestival.getNom());
        Long insertion = db.insert(nomTableFestivals, null, values);
    }

    public void AjouterQuestion(Questions uneQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idQuestion, uneQuestions.getId());
        values.put(titre, uneQuestions.getTitre());
        values.put(idConcourFk, uneQuestions.getUnConcour().getId());
        Long insertion = db.insert(nomTableQuestions, null, values);
    }

    public void AjouterProposition(Proposition uneProposition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idProposition, uneProposition.getId());
        values.put(intitule, uneProposition.getIntitule());
        Long insertion = db.insert(nomTableProposition, null, values);
    }

    public void AjouterRepondre(Repondre uneReponse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idQuestionFk, uneReponse.getUneQuestion().getId());
        values.put(idPropositionFk, uneReponse.getUneProposition().getId());
        values.put(boolReponse,uneReponse.getReponse());
        Long insertion = db.insert(nomTableRepondre, null, values);
    }

    public void AjouterConcour(Concours unConcours) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(nomConcour, unConcours.getNom());
        values.put(dateDebutConcour, unConcours.getDateDebut().getTime());
        values.put(dateFinConcour, unConcours.getDateFin().getTime());
        values.put(recompence, unConcours.getRecompence());
        values.put(idFestivalFk, unConcours.getUnFestival().getId());
        Long insertion = db.insert(nomTableConcours, null, values);
    }

    public void AjouterParticipe(Participe uneParticipation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idConcourFk, uneParticipation.getUnConcour().getId());
        values.put(idUtilisateurFk, uneParticipation.getUnUtilisateur().getId());
        //values.put(departParticipation, dateFormat.format(uneParticipation.getDepart()));
        //DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
        values.put(departParticipation,uneParticipation.getDepart().getTime());

        Long insertion = db.insert(nomTableParticipe, null, values);
    }

    public void AjouterParticiper(Participe uneParticipation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idConcourFk, uneParticipation.getUnConcour().getId());
        values.put(idUtilisateurFk, uneParticipation.getUnUtilisateur().getId());
        values.put(score, uneParticipation.getScore());

        //values.put(departParticipation, dateFormat.format(uneParticipation.getDepart()));
        //DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        values.put(departParticipation,uneParticipation.getDepart().getTime());
        values.put(finParticipation, uneParticipation.getFin().getTime());
        Long insertion = db.insert(nomTableParticipe, null, values);
    }

    public Long AjouterChoix(Choix unChoix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(choixUtilisateur, unChoix.getChoix());
        values.put(valide, unChoix.getValide());
        values.put(idUtilisateurFk, unChoix.getUnUtilisateur().getId());
        values.put(idQuestionFk, unChoix.getUneQuestion().getId());
        Long insertion = db.insert(nomTableChoix, null, values);
        return insertion;
    }

    public Long AjouterTypeUtilisateur(TypeUtilisateur unType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(idTypeUtilisateur, unType.getId());
        values.put(libelle, unType.getLibelle());
        Long insertion = db.insert(nomTableTypeUtilisateur, null, values);
        return insertion;
    }

    public ArrayList<Utilisateurs> getLesUtilisateurs() {
        ArrayList<Utilisateurs> ensUtilisateurs = new ArrayList<Utilisateurs>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Utilisateurs, Type_utilisateur WHERE id_type_utilisateur_fk = id_type_utilisateur;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Utilisateurs un_utilisateurs = new Utilisateurs();
                un_utilisateurs.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateur)));
                un_utilisateurs.setPseudo(unCurseur.getString(unCurseur.getColumnIndex(pseudo)));
                un_utilisateurs.setMotDePasse(unCurseur.getString(unCurseur.getColumnIndex(motDePasse)));
                un_utilisateurs.setPrenom(unCurseur.getString(unCurseur.getColumnIndex(prenom)));
                un_utilisateurs.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
                un_utilisateurs.setPays(unCurseur.getString(unCurseur.getColumnIndex(pays)));
                un_utilisateurs.setVille(unCurseur.getString(unCurseur.getColumnIndex(ville)));
                un_utilisateurs.setAdresse(unCurseur.getString(unCurseur.getColumnIndex(adresse)));
                un_utilisateurs.setCodePostal(unCurseur.getString(unCurseur.getColumnIndex(codePostal)));
                un_utilisateurs.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                un_utilisateurs.setTelephone(unCurseur.getString(unCurseur.getColumnIndex(telephone)));

                TypeUtilisateur unType = new TypeUtilisateur();
                unType.setId(unCurseur.getInt(unCurseur.getColumnIndex(idTypeUtilisateurFk)));
                unType.setLibelle(unCurseur.getString(unCurseur.getColumnIndex(libelle)));
                un_utilisateurs.setType(unType);
                ensUtilisateurs.add(un_utilisateurs);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensUtilisateurs);
        }
        return ensUtilisateurs;
    }

    public ArrayList<Utilisateurs> getLesUtilisateursAdmin() {
        ArrayList<Utilisateurs> ensUtilisateurs = new ArrayList<Utilisateurs>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Utilisateurs, Type_utilisateur WHERE id_type_utilisateur_fk = id_type_utilisateur AND id_type_utilisateur <> 1;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Utilisateurs un_utilisateurs = new Utilisateurs();
                un_utilisateurs.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateur)));
                un_utilisateurs.setPseudo(unCurseur.getString(unCurseur.getColumnIndex(pseudo)));
                un_utilisateurs.setMotDePasse(unCurseur.getString(unCurseur.getColumnIndex(motDePasse)));
                un_utilisateurs.setPrenom(unCurseur.getString(unCurseur.getColumnIndex(prenom)));
                un_utilisateurs.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
                un_utilisateurs.setPays(unCurseur.getString(unCurseur.getColumnIndex(pays)));
                un_utilisateurs.setVille(unCurseur.getString(unCurseur.getColumnIndex(ville)));
                un_utilisateurs.setAdresse(unCurseur.getString(unCurseur.getColumnIndex(adresse)));
                un_utilisateurs.setCodePostal(unCurseur.getString(unCurseur.getColumnIndex(codePostal)));
                un_utilisateurs.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
                un_utilisateurs.setTelephone(unCurseur.getString(unCurseur.getColumnIndex(telephone)));

                TypeUtilisateur unType = new TypeUtilisateur();
                unType.setId(unCurseur.getInt(unCurseur.getColumnIndex(idTypeUtilisateurFk)));
                unType.setLibelle(unCurseur.getString(unCurseur.getColumnIndex(libelle)));
                un_utilisateurs.setType(unType);
                ensUtilisateurs.add(un_utilisateurs);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensUtilisateurs);
        }
        return ensUtilisateurs;
    }

    public ArrayList<Festivals> getLesFestivals() {
        ArrayList<Festivals> ensFestival = new ArrayList<Festivals>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Festivals;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Festivals un_festival = new Festivals();
                un_festival.setId(unCurseur.getInt(unCurseur.getColumnIndex(idFestival)));
                un_festival.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomFestival)));
                ensFestival.add(un_festival);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensFestival);
        }
        return ensFestival;
    }

    public ArrayList<Questions> getLesQuestion(int idConcour) {
        HashMap<Integer, Questions> ensQuestions = new HashMap<Integer, Questions>();
        ArrayList<Repondre> l = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM  Repondre, Proposition, Questions  WHERE id_question = id_question_fk AND id_proposition_fk = id_proposition AND id_concour_fk = '" + idConcour + "'  ;", null);
        if(unCurseur.moveToFirst())
        {
            do
            {
                Questions une_questions = new Questions();
                une_questions.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)));
                une_questions.setTitre(unCurseur.getString(unCurseur.getColumnIndex(titre)));
                Log.e("tagaga", une_questions.getTitre());
                ensQuestions.put(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)), une_questions);

                Proposition une_proposition = new Proposition();
                une_proposition.setId(unCurseur.getInt(unCurseur.getColumnIndex(idProposition)));
                une_proposition.setIntitule(unCurseur.getString(unCurseur.getColumnIndex(intitule)));

                Repondre une_reponse = new Repondre();
                boolean reponse = false ;
                int reponseBool = unCurseur.getInt(unCurseur.getColumnIndex(boolReponse));

                if(reponseBool == 1)
                { reponse = true;}

                une_reponse.setReponse(reponse);
                une_reponse.setUneProposition(une_proposition);
                une_reponse.setUneQuestion(une_questions);

                l.add(une_reponse);

            }
            while (unCurseur.moveToNext());
            //Collections.shuffle(ensQuestions);
        }
        for (Repondre u : l)
        {
            if(ensQuestions.containsKey(u.getUneQuestion().getId()))
            {
             ensQuestions.get(u.getUneQuestion().getId()).getListeReponse().add(u);
            }
        }
        //for(Questions rep : ensQuestions.values())
        //{ Log.e("tagu", rep.getTitre());
          //  for(Repondre rep2 : rep.getListeReponse())
            //{
              //  Log.e("tag", String.valueOf(rep2.getUneProposition().getId() + rep2.getUneProposition().getIntitule() + rep2.getReponse()));
            //}
        //}
        ArrayList<Questions> listeQuestions = new ArrayList<>();
        for(Questions uneQuestion : ensQuestions.values())
        {
            listeQuestions.add(uneQuestion);
        }
        return listeQuestions;
    }

    public Concours getLeConcour(int idConcour) {
        Concours unConcour = new Concours();
        HashMap<Integer, Questions> ensQuestions = new HashMap<Integer, Questions>();
        ArrayList<Repondre> l = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM  Concours, Repondre, Proposition, Questions  WHERE id_concour = id_concour_fk AND id_question = id_question_fk AND id_proposition_fk = id_proposition AND id_concour = '" + idConcour + "'  ;", null);
        if(unCurseur.moveToFirst())
        {
            unConcour.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomConcour)));
            Date dateDebut = new Date(unCurseur.getLong(unCurseur.getColumnIndex(dateDebutConcour)));
            unConcour.setDateDebut(dateDebut);
            Date dateFin = new Date(unCurseur.getLong(unCurseur.getColumnIndex(dateFinConcour)));
            unConcour.setDateFin(dateFin);
            unConcour.setRecompence(unCurseur.getString(unCurseur.getColumnIndex(recompence)));
            do
            {
                Questions une_questions = new Questions();
                une_questions.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)));
                une_questions.setTitre(unCurseur.getString(unCurseur.getColumnIndex(titre)));
                Log.e("tagaga", une_questions.getTitre());
                ensQuestions.put(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)), une_questions);

                Proposition une_proposition = new Proposition();
                une_proposition.setId(unCurseur.getInt(unCurseur.getColumnIndex(idProposition)));
                une_proposition.setIntitule(unCurseur.getString(unCurseur.getColumnIndex(intitule)));

                Repondre une_reponse = new Repondre();
                boolean reponse = false ;
                int reponseBool = unCurseur.getInt(unCurseur.getColumnIndex(boolReponse));

                if(reponseBool == 1)
                { reponse = true;}

                une_reponse.setReponse(reponse);
                une_reponse.setUneProposition(une_proposition);
                une_reponse.setUneQuestion(une_questions);

                l.add(une_reponse);

            }
            while (unCurseur.moveToNext());
            //Collections.shuffle(ensQuestions);
        }
        for (Repondre u : l)
        {
            if(ensQuestions.containsKey(u.getUneQuestion().getId()))
            {
                ensQuestions.get(u.getUneQuestion().getId()).getListeReponse().add(u);
            }
        }
        ArrayList<Questions> listeQuestions = new ArrayList<>();
        for(Questions uneQuestion : ensQuestions.values())
        {
            listeQuestions.add(uneQuestion);
        }
        unConcour.setListeQuestions(listeQuestions);
        return unConcour;
    }

    public ArrayList<Questions> getLesQuestionStats(int idConcour) {
        HashMap<Integer, Questions> ensQuestions = new HashMap<Integer, Questions>();
        ArrayList<Choix> l = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Questions, Choix  WHERE id_question = id_question_fk AND id_concour_fk = '" + idConcour + "'  ;", null);
        if(unCurseur.moveToFirst())
        {
            do
            {
                Questions une_questions = new Questions();
                une_questions.setId(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)));
                une_questions.setTitre(unCurseur.getString(unCurseur.getColumnIndex(titre)));
                Log.e("tagaga", une_questions.getTitre());
                ensQuestions.put(unCurseur.getInt(unCurseur.getColumnIndex(idQuestion)), une_questions);

                Choix un_choix = new Choix();
                boolean reponseValide = false ;
                int reponseBool = unCurseur.getInt(unCurseur.getColumnIndex(valide));

                if(reponseBool == 1)
                { reponseValide = true;}

                un_choix.setValide(reponseValide);
                un_choix.setUneQuestion(une_questions);
                l.add(un_choix);

            }
            while (unCurseur.moveToNext());
            //Collections.shuffle(ensQuestions);
        }
        for (Choix u : l)
        {
            if(ensQuestions.containsKey(u.getUneQuestion().getId()))
            {
                ensQuestions.get(u.getUneQuestion().getId()).getListeChoix().add(u);
            }
        }

        ArrayList<Questions> listeQuestions = new ArrayList<>();
        for(Questions uneQuestion : ensQuestions.values())
        {
            listeQuestions.add(uneQuestion);
        }

        return listeQuestions;
    }
    public ArrayList<Proposition> getLesProposition(int idConcour) {
        ArrayList<Proposition> ensProposition = new ArrayList<Proposition>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Proposition;", null);
        if(unCurseur.moveToFirst())
        {
            do
            {
                Proposition une_proposition = new Proposition();
                une_proposition.setId(unCurseur.getInt(unCurseur.getColumnIndex(idProposition)));
                une_proposition.setIntitule(unCurseur.getString(unCurseur.getColumnIndex(intitule)));
                ensProposition.add(une_proposition);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensProposition);
        }
        return ensProposition;
    }
    public ArrayList<Participe> getLesParticipation() {
        ArrayList<Participe> ensParticipe = new ArrayList<Participe>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Participe;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Participe une_participation = new Participe();
                une_participation.setScore(unCurseur.getInt(unCurseur.getColumnIndex(score)));

                Concours un_concour = new Concours();
                un_concour.setId(unCurseur.getInt(unCurseur.getColumnIndex(idConcourFk)));
                une_participation.setUnConcour(un_concour);

                Utilisateurs un_utilisateur = new Utilisateurs();
                un_utilisateur.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateurFk)));
                une_participation.setUnUtilisateur(un_utilisateur);

                ensParticipe.add(une_participation);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensParticipe);
        }
        return ensParticipe;
    }
    public ArrayList<TypeUtilisateur> getLesType() {
        ArrayList<TypeUtilisateur> ensType = new ArrayList<TypeUtilisateur>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Type_utilisateur;", null);
        if (unCurseur.moveToFirst()) {
            do {
                TypeUtilisateur un_type = new TypeUtilisateur();
                un_type.setId(unCurseur.getInt(unCurseur.getColumnIndex(idTypeUtilisateur)));
                un_type.setLibelle(libelle);
                ensType.add(un_type);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensType);
        }
        return ensType;
    }
    public ArrayList<Participe> getLesParticipationUtilisateur(int idUtilisateur) {
        ArrayList<Participe> ensParticipe = new ArrayList<Participe>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Participe, Concours, Festivals WHERE id_concour_fk = id_concour AND id_festival = id_festival_fk AND id_utilisateur_fk = "+idUtilisateur+" ;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Participe une_participation = new Participe();
                une_participation.setScore(unCurseur.getInt(unCurseur.getColumnIndex(score)));

                Concours un_concour = new Concours();
                Festivals unFestival = new Festivals();

                unFestival.setId(unCurseur.getInt(unCurseur.getColumnIndex(idFestival)));
                unFestival.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomFestival)));

                un_concour.setId(unCurseur.getInt(unCurseur.getColumnIndex(idConcourFk)));
                un_concour.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomConcour)));

                un_concour.setUnFestival(unFestival);
                une_participation.setUnConcour(un_concour);

                Utilisateurs un_utilisateur = new Utilisateurs();
                un_utilisateur.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateurFk)));
                une_participation.setUnUtilisateur(un_utilisateur);

                ensParticipe.add(une_participation);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensParticipe);
        }
        return ensParticipe;
    }
    public ArrayList<Participe> getLesParticipationConcours(int idConcour) {
        ArrayList<Participe> ensParticipe = new ArrayList<Participe>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Participe, Concours, Utilisateurs WHERE id_utilisateur_fk = id_utilisateur AND id_concour = id_concour_fk AND id_concour_fk = "+idConcour+" ORDER BY score DESC;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Participe une_participation = new Participe();
                une_participation.setScore(unCurseur.getInt(unCurseur.getColumnIndex(score)));

                Concours un_concour = new Concours();
                un_concour.setId(unCurseur.getInt(unCurseur.getColumnIndex(idConcourFk)));
                un_concour.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomConcour)));
                un_concour.setRecompence(unCurseur.getString(unCurseur.getColumnIndex(recompence)));

                Utilisateurs un_utilisateur = new Utilisateurs();
                un_utilisateur.setPseudo(unCurseur.getString(unCurseur.getColumnIndex(pseudo)));
                un_utilisateur.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateurFk)));

                une_participation.setUnConcour(un_concour);
                une_participation.setUnUtilisateur(un_utilisateur);

                Long debu = unCurseur.getLong(unCurseur.getColumnIndex(departParticipation));
                Date dateDebut = new Date(debu);
                une_participation.setDepart(dateDebut);

                Long dateFin=  unCurseur.getLong(unCurseur.getColumnIndex(finParticipation));
                Date uneFin = new Date(dateFin);
                une_participation.setFin(uneFin);

                ensParticipe.add(une_participation);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensParticipe);
        }
        return ensParticipe;
    }
    public ArrayList<Concours> getLesConcours() {
        ArrayList<Concours> ensConcours = new ArrayList<Concours>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Concours , Festivals WHERE id_festival = id_festival_fk;", null);
        if (unCurseur.moveToFirst()) {
            do {

                Concours un_concours = new Concours();
                un_concours.setId(unCurseur.getInt(unCurseur.getColumnIndex(idConcour)));
                un_concours.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomConcour)));

                long dateDebut=  unCurseur.getLong(unCurseur.getColumnIndex(dateDebutConcour));
                Date dateDebut2 = new Date(dateDebut);
                un_concours.setDateDebut(dateDebut2);

                long dateFin=  unCurseur.getLong(unCurseur.getColumnIndex(dateFinConcour));
                Date dateFin2 = new Date(dateFin);
                un_concours.setDateFin(dateFin2);

                un_concours.setRecompence(unCurseur.getString(unCurseur.getColumnIndex(recompence)));

                Festivals unFestival = new Festivals(unCurseur.getInt(unCurseur.getColumnIndex(idFestival)), unCurseur.getString(unCurseur.getColumnIndex(nomFestival)));
                un_concours.setUnFestival(unFestival);
                ensConcours.add(un_concours);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensConcours);
        }
        return ensConcours;
    }
    public ArrayList<Concours> getLesConcours(int idUtilisateur) {
        ArrayList<Concours> ensConcours = new ArrayList<Concours>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Concours, Festivals WHERE id_festival = id_festival_fk AND id_concour NOT IN (SELECT id_concour_fk FROM participe WHERE id_utilisateur_fk = "+idUtilisateur+") ;", null);
        if (unCurseur.moveToFirst()) {
            do {
                Concours un_concours = new Concours();
                un_concours.setId(unCurseur.getInt(unCurseur.getColumnIndex(idConcour)));
                un_concours.setNom(unCurseur.getString(unCurseur.getColumnIndex(nomConcour)));

                long dateDebut=  unCurseur.getLong(unCurseur.getColumnIndex(dateDebutConcour));
                Date dateDebut2 = new Date(dateDebut);
                un_concours.setDateDebut(dateDebut2);

                long dateFin=  unCurseur.getLong(unCurseur.getColumnIndex(dateFinConcour));
                Date dateFin2 = new Date(dateFin);
                un_concours.setDateFin(dateFin2);

                un_concours.setRecompence(unCurseur.getString(unCurseur.getColumnIndex(recompence)));

                Festivals unFestival = new Festivals(unCurseur.getInt(unCurseur.getColumnIndex(idFestival)), unCurseur.getString(unCurseur.getColumnIndex(nomFestival)));
                un_concours.setUnFestival(unFestival);
                ensConcours.add(un_concours);
            }
            while (unCurseur.moveToNext());
            Collections.shuffle(ensConcours);
        }
        return ensConcours;
    }
    public Utilisateurs Connexion(String wpseudo, String wmotPasse) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Utilisateurs, Type_utilisateur WHERE pseudo = '" + wpseudo + "' AND mot_de_passe = '" + wmotPasse + "' AND id_type_utilisateur_fk = id_type_utilisateur ;", null);
        Utilisateurs un_utilisateurs = new Utilisateurs();
        int nbrRec = unCurseur.getCount();
        if (nbrRec == 0)
        {
        }
        else {
            unCurseur.moveToFirst();
            un_utilisateurs.setId(unCurseur.getInt(unCurseur.getColumnIndex(idUtilisateur)));
            un_utilisateurs.setPseudo(unCurseur.getString(unCurseur.getColumnIndex(pseudo)));
            un_utilisateurs.setMotDePasse(unCurseur.getString(unCurseur.getColumnIndex(motDePasse)));
            un_utilisateurs.setPrenom(unCurseur.getString(unCurseur.getColumnIndex(prenom)));
            un_utilisateurs.setNom(unCurseur.getString(unCurseur.getColumnIndex(nom)));
            un_utilisateurs.setPays(unCurseur.getString(unCurseur.getColumnIndex(pays)));
            un_utilisateurs.setVille(unCurseur.getString(unCurseur.getColumnIndex(ville)));
            un_utilisateurs.setAdresse(unCurseur.getString(unCurseur.getColumnIndex(adresse)));
            un_utilisateurs.setCodePostal(unCurseur.getString(unCurseur.getColumnIndex(codePostal)));
            un_utilisateurs.setMail(unCurseur.getString(unCurseur.getColumnIndex(mail)));
            un_utilisateurs.setTelephone(unCurseur.getString(unCurseur.getColumnIndex(telephone)));

            TypeUtilisateur unType = new TypeUtilisateur();
            unType.setId(unCurseur.getInt(unCurseur.getColumnIndex(idTypeUtilisateurFk)));
            unType.setLibelle(unCurseur.getString(unCurseur.getColumnIndex(libelle)));
            un_utilisateurs.setType(unType);
        }
        return un_utilisateurs;
    }
    public int getNbFestivals() {
        int nbFestival = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Festival;", null);
        nbFestival = unCurseur.getColumnCount();
        return nbFestival;
    }
    public int getDernierIdConcour() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT MAX(id_concour) as nbr FROM Concours;", null);
        unCurseur.moveToFirst();
        int idDernierConcour = unCurseur.getInt(unCurseur.getColumnIndex("nbr"));
        return idDernierConcour;
    }
    public int getDernierIdQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT MAX(id_question) as nbr FROM Questions;", null);
        unCurseur.moveToFirst();
        int idDernierConcour = unCurseur.getInt(unCurseur.getColumnIndex("nbr"));
        return idDernierConcour;
    }
    public int getDernierIdProposition() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT MAX(id_proposition) as nbr FROM Proposition;", null);
        unCurseur.moveToFirst();
        int idDernierConcour = unCurseur.getInt(unCurseur.getColumnIndex("nbr"));
        return idDernierConcour;
    }

    public int getNbReponseValide(int idUtilisateur, int idConcour) {
        int nbReponse = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT * FROM Choix, Questions WHERE id_question_fk = id_question AND id_utilisateur_fk = "+idUtilisateur+" AND id_concour_fk = "+idConcour+" AND valide = 1;", null);
        nbReponse = unCurseur.getCount();
        return nbReponse;
    }

    public ArrayList<Integer> getNbQuestionNbReponse(int idConcour) {

        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor unCurseur = db.rawQuery("SELECT count(distinct(id_question)) as nbQuestion, count(id_question)/count(distinct(id_question)) as nbReponse FROM Questions, Repondre, Proposition WHERE id_question = id_question_fk AND id_proposition = id_proposition_fk AND id_concour_fk = "+idConcour+";", null);

        unCurseur.moveToFirst();

        list.add(unCurseur.getInt(unCurseur.getColumnIndex("nbQuestion")));
        list.add(unCurseur.getInt(unCurseur.getColumnIndex("nbReponse")));
        Log.e("tag", String.valueOf(list.get(0)));
        Log.e("tag", String.valueOf(list.get(1)));

        return list;
    }

    public void supprimerFestival(int idDuFestival) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String aSup =  String.valueOf(idDuFestival);
        db.delete(nomTableFestivals,"id_festival=?",new String[]{aSup});
    }

    public void supprimerUtilisateur(int idDeLutilisateur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String aSup =  String.valueOf(idDeLutilisateur);
        db.delete(nomTableUtilisateurs,"id_utilisateur=?",new String[]{aSup});
    }

    public void supprimerConcour(int idDeConcour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String aSup =  String.valueOf(idDeConcour);
        db.delete(nomTableConcours,"id_concour=?",new String[]{aSup});
    }

    public void MettreAJourParticipe(Participe uneParticipation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(score, uneParticipation.getScore());
        values.put(finParticipation, uneParticipation.getFin().getTime());

        String valeur1 =String.valueOf(uneParticipation.getUnConcour().getId());
        String valeur2 = String.valueOf(uneParticipation.getUnUtilisateur().getId());
        db.update(nomTableParticipe, values,"id_concour_fk=? AND id_utilisateur_fk=?",new String[]{valeur1,valeur2});
        //db.execSQL("UPDATE "+nomTableParticipe+" SET score = "+ uneParticipation.getScore() +", fin_participation = "+dateFormat.format(uneParticipation.getFin().getTime())+" WHERE id_concour_fk = "+uneParticipation.getUnConcour().getId()+" AND  id_utilisateur_fk = "+uneParticipation.getUnUtilisateur().getId());
    }

    public void modifConcours(Concours unConcour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dateDebutConcour, unConcour.getDateDebut().getTime());
        values.put(dateFinConcour, unConcour.getDateFin().getTime());
        values.put(nomConcour, unConcour.getNom());
        values.put(recompence, unConcour.getRecompence());

        String valeur1 =String.valueOf(unConcour.getId());
        db.update(nomTableConcours, values,"id_concour=?",new String[]{valeur1});
        //db.execSQL("UPDATE "+nomTableParticipe+" SET score = "+ uneParticipation.getScore() +", fin_participation = "+dateFormat.format(uneParticipation.getFin().getTime())+" WHERE id_concour_fk = "+uneParticipation.getUnConcour().getId()+" AND  id_utilisateur_fk = "+uneParticipation.getUnUtilisateur().getId());
    }

    public void modifQuestion(Questions uneQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(titre, uneQuestions.getTitre());
        values.put(idConcourFk, uneQuestions.getUnConcour().getId());
        String valeur1 =String.valueOf(uneQuestions.getId());
        db.update(nomTableRepondre, values,"id_question=?",new String[]{valeur1});
        //db.execSQL("UPDATE "+nomTableParticipe+" SET score = "+ uneParticipation.getScore() +", fin_participation = "+dateFormat.format(uneParticipation.getFin().getTime())+" WHERE id_concour_fk = "+uneParticipation.getUnConcour().getId()+" AND  id_utilisateur_fk = "+uneParticipation.getUnUtilisateur().getId());
    }

    public void modifProposition(Proposition uneProposition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(intitule, uneProposition.getIntitule());

        String valeur1 =String.valueOf(uneProposition.getId());
        db.update(nomTableProposition, values,"id_proposition=?",new String[]{valeur1});
        //db.execSQL("UPDATE "+nomTableParticipe+" SET score = "+ uneParticipation.getScore() +", fin_participation = "+dateFormat.format(uneParticipation.getFin().getTime())+" WHERE id_concour_fk = "+uneParticipation.getUnConcour().getId()+" AND  id_utilisateur_fk = "+uneParticipation.getUnUtilisateur().getId());
    }

    public void modifReponse(Repondre uneReponse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(valide, uneReponse.getReponse());

        String valeur1 =String.valueOf(uneReponse.getUneProposition().getId());
        String valeur2 =String.valueOf(uneReponse.getUneQuestion().getId());
        db.update(nomTableRepondre, values,"id_proposition_fk=? AND id_question_fk=?",new String[]{valeur1, valeur2});
        //db.execSQL("UPDATE "+nomTableParticipe+" SET score = "+ uneParticipation.getScore() +", fin_participation = "+dateFormat.format(uneParticipation.getFin().getTime())+" WHERE id_concour_fk = "+uneParticipation.getUnConcour().getId()+" AND  id_utilisateur_fk = "+uneParticipation.getUnUtilisateur().getId());
    }
}



