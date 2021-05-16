package com.example.monapplication.GestionBdd;
import android.content.Context;
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
import java.util.Comparator;
import java.util.Date;
import android.util.Log;

public class ensemble {
    ArrayList<Utilisateurs> ensUtilisateurs = new ArrayList<>();
    ArrayList<Festivals> ensFestival = new ArrayList<>();
    ArrayList<Questions> ensQuestions = new ArrayList<>();
    ArrayList<Concours> ensConcours = new ArrayList<>();
    ArrayList<Participe> ensParticipation = new ArrayList<>();
    ArrayList<TypeUtilisateur> ensType= new ArrayList<>();
    ArrayList<Proposition> ensProposition= new ArrayList<>();
    ArrayList<Repondre> ensRepondre = new ArrayList<>();
    GestionBdd base_Utilisateur;


    public void creationBdd_utilisateur(Context un_context) {
        base_Utilisateur = new GestionBdd(un_context);
        ensUtilisateurs = base_Utilisateur.getLesUtilisateurs();
        if (ensUtilisateurs.isEmpty()) {

            TypeUtilisateur unType = new TypeUtilisateur(1, "admin");
            TypeUtilisateur deuxType = new TypeUtilisateur(2, "utilisateur");

            Date dateNaiss = new Date(2000-1900, 00, 00);
            base_Utilisateur.AjouterUtilisateur(new Utilisateurs(1, "Clement", "Clement", "Clement",  "Chevreuil",dateNaiss, "France", "Ligueil", "11 place du champ de foire", "37240", "clement.chevreuil2000@gmail.com", "06.23.13.01.39", unType));
            base_Utilisateur.AjouterUtilisateur(new Utilisateurs(2, "Florian", "Florian", "Florian", "Herault", dateNaiss, "France", "Tours", "100 rue d'amboise", "37240", "clement.chevreuil2000@gmail.com", "06.23.13.01.39", deuxType));
            ensUtilisateurs = base_Utilisateur.getLesUtilisateurs();
        }
    }
    public void creationBdd_typeUtilisateur(Context un_context) {
        base_Utilisateur = new GestionBdd(un_context);
        ensUtilisateurs = base_Utilisateur.getLesUtilisateurs();
        if (ensUtilisateurs.isEmpty()) {

            base_Utilisateur.AjouterTypeUtilisateur(new TypeUtilisateur(1, "admin"));
            base_Utilisateur.AjouterTypeUtilisateur(new TypeUtilisateur(2, "utilisateur"));
            ensType = base_Utilisateur.getLesType();
        }
    }
    public void creationBdd_festival(Context un_context) {
        base_Utilisateur = new GestionBdd(un_context);
        ensFestival = base_Utilisateur.getLesFestivals();
        if (ensFestival.isEmpty()) {
            base_Utilisateur.AjouterFestival(new Festivals(1, "Lisons tous ensemble"));
            base_Utilisateur.AjouterFestival(new Festivals(2, "Un amour de lire"));
            ensFestival = base_Utilisateur.getLesFestivals();
        }
    }
    public void creationBdd_concours(Context un_context){
        base_Utilisateur = new GestionBdd(un_context);
        ensConcours = base_Utilisateur.getLesConcours();
        if(ensConcours.isEmpty()){

            Festivals unFestival = new Festivals(1, "Lisons tous ensemble");
            Festivals deuxFestival = new Festivals(2, "Un amour de lire");

            Date uneDateDebut  = new Date(2020-1900,01,01);
            Date uneDateFin  = new Date(2020-1900,02,01);

            Date deuxDateDebut  = new Date(2020-1900,03,01);
            Date deuxDateFin  = new Date(2020-1900,06,01);

            Concours unConcour = new Concours(1,"Concours Tintin",deuxDateDebut, deuxDateFin,"Collection de BD tintin", unFestival);
            Concours deuxConcour = new Concours(2, "Concours Mangas",deuxDateDebut, deuxDateFin,"Collection de manga Dragon ball", deuxFestival);

            Proposition uneProposition = new Proposition(1, "bleu");
            Proposition deuxProposition = new Proposition(2, "rouge");
            Proposition troisProposition = new Proposition(3, "noir");
            Proposition quatreProposition = new Proposition(4, "oui");
            Proposition cinqProposition = new Proposition(5, "non");
            Proposition sixProposition = new Proposition(6, "peut etre");

            Questions question1 = new Questions(1,"Quel est ma couleur préféré ?",unConcour);
            Questions question2 = new Questions(2,"Quel est la couleur que je deteste?",unConcour) ;
            Questions question3 = new Questions(3,"Quel couleur m'importe peu?",unConcour);
            Questions question4 = new Questions(4,"la terre est ronde ?", deuxConcour);
            Questions question5 = new Questions(5,"le soleil tourne autour de la terre ?", deuxConcour);
            Questions question6 = new Questions(6,"l'humain est il immortel ?", deuxConcour);

            base_Utilisateur.AjouterConcour(unConcour);
            base_Utilisateur.AjouterConcour(deuxConcour);

            base_Utilisateur.AjouterProposition(uneProposition);
            base_Utilisateur.AjouterProposition(deuxProposition);
            base_Utilisateur.AjouterProposition(troisProposition);
            base_Utilisateur.AjouterProposition(quatreProposition);
            base_Utilisateur.AjouterProposition(cinqProposition);
            base_Utilisateur.AjouterProposition(sixProposition);

            base_Utilisateur.AjouterQuestion(question1);
            base_Utilisateur.AjouterQuestion(question2);
            base_Utilisateur.AjouterQuestion(question3);
            base_Utilisateur.AjouterQuestion(question4);
            base_Utilisateur.AjouterQuestion(question5);
            base_Utilisateur.AjouterQuestion(question6);

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question1,true));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question1,false));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question1,false));

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question2,false));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question2,true));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question2,false));

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question3,false));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question3,false));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question3,true));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question4,true));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question4,false));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question4,false));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question5,false));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question5,true));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question5,false));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question6,false));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question6,false));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question6,true));


            ensConcours = base_Utilisateur.getLesConcours();
        }
    }
    public void creationBdd_concours2(Context un_context, int id){
        base_Utilisateur = new GestionBdd(un_context);
        ensQuestions = base_Utilisateur.getLesQuestion(id);
        if(ensQuestions.isEmpty()){

            Festivals unFestival = new Festivals(1, "Rock&Roll");
            Festivals deuxFestival = new Festivals(2, "Vieille charue");

            Date uneDateDebut  = new Date(2020-1900,01,01);
            Date uneDateFin  = new Date(2020-1900,02,01);

            Date deuxDateDebut  = new Date(2020-1900,03,01);
            Date deuxDateFin  = new Date(2020-1900,06,01);

            Concours unConcour = new Concours(1,"Premier Concour",deuxDateDebut, deuxDateFin,"rec", unFestival);
            Concours deuxConcour = new Concours(2, "Second Concour",deuxDateDebut, deuxDateFin,"deuxRecompence", deuxFestival);

            Proposition uneProposition = new Proposition(1, "bleu");
            Proposition deuxProposition = new Proposition(2, "rouge");
            Proposition troisProposition = new Proposition(3, "noir");
            Proposition quatreProposition = new Proposition(4, "oui");
            Proposition cinqProposition = new Proposition(5, "non");
            Proposition sixProposition = new Proposition(6, "peut etre");

            Questions question1 = new Questions(1,"Quel est ma couleur préféré ?",unConcour);
            Questions question2 = new Questions(2,"Quel est la couleur que je deteste?",unConcour) ;
            Questions question3 = new Questions(3,"Quel couleur m'importe peu?",unConcour);
            Questions question4 = new Questions(4,"la terre est ronde ?", deuxConcour);
            Questions question5 = new Questions(5,"le soleil tourne autour de la terre ?", deuxConcour);
            Questions question6 = new Questions(6,"l'humain est il immortel ?", deuxConcour);

            base_Utilisateur.AjouterConcour(unConcour);
            base_Utilisateur.AjouterConcour(deuxConcour);

            base_Utilisateur.AjouterProposition(uneProposition);
            base_Utilisateur.AjouterProposition(deuxProposition);
            base_Utilisateur.AjouterProposition(troisProposition);
            base_Utilisateur.AjouterProposition(quatreProposition);
            base_Utilisateur.AjouterProposition(cinqProposition);
            base_Utilisateur.AjouterProposition(sixProposition);

            base_Utilisateur.AjouterQuestion(question1);
            base_Utilisateur.AjouterQuestion(question2);
            base_Utilisateur.AjouterQuestion(question3);
            base_Utilisateur.AjouterQuestion(question4);
            base_Utilisateur.AjouterQuestion(question5);
            base_Utilisateur.AjouterQuestion(question6);

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question1,true));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question1,false));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question1,false));

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question2,false));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question2,true));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question2,false));

            base_Utilisateur.AjouterRepondre(new Repondre(uneProposition, question3,false));
            base_Utilisateur.AjouterRepondre(new Repondre(deuxProposition, question3,false));
            base_Utilisateur.AjouterRepondre(new Repondre(troisProposition, question3,true));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question4,true));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question4,false));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question4,false));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question5,false));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question5,true));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question5,false));

            base_Utilisateur.AjouterRepondre(new Repondre(quatreProposition, question6,false));
            base_Utilisateur.AjouterRepondre(new Repondre(cinqProposition, question6,false));
            base_Utilisateur.AjouterRepondre(new Repondre(sixProposition, question6,true));


            ensQuestions = base_Utilisateur.getLesQuestion(id);
        }
    }
    public void creationBdd_participe(Context un_context){
        base_Utilisateur = new GestionBdd(un_context);
        ensParticipation = base_Utilisateur.getLesParticipation();
        if(ensParticipation.isEmpty()){


            Concours unConcour = new Concours(1,"Premier Concour");
            Concours deuxConcour = new Concours(2,"Second Concour");

            Date uneDateDebut  = new Date(2019,1,1,1,1,1);
            Date uneDateFi  = new Date(2020,1,1,2,1,1);

            TypeUtilisateur unType = new TypeUtilisateur(1, "admin");
            TypeUtilisateur deuxType = new TypeUtilisateur(2, "utilisateur");

            Utilisateurs deuxUtilisateur = new Utilisateurs(2, "Florian", "Florian", deuxType);
            base_Utilisateur.AjouterParticiper(new Participe(unConcour,deuxUtilisateur, uneDateDebut, uneDateDebut,1));
            base_Utilisateur.AjouterParticiper(new Participe(deuxConcour,deuxUtilisateur,uneDateDebut, uneDateFi,2));
            ensParticipation = base_Utilisateur.getLesParticipation();
        }
    }

    public void AjouterChoix (Choix unChoix)
    {
        base_Utilisateur.AjouterChoix(unChoix);
    }
    public void AjouterFestival (Festivals unFestival) { base_Utilisateur.AjouterFestival(unFestival); }
    public void AjouterConcour(Concours unConcour) { base_Utilisateur.AjouterConcour(unConcour); }
    public void AjouterParticipe(Participe uneParticipation) { base_Utilisateur.AjouterParticipe(uneParticipation); }
    public void AjouterQuestion(Questions question) { base_Utilisateur.AjouterQuestion(question); }
    public void AjouterParticiper(Participe uneParticipation) { base_Utilisateur.AjouterParticipe(uneParticipation); }
    public void AjouterUtilisateur(Utilisateurs unUtilisateur) { base_Utilisateur.AjouterUtilisateur(unUtilisateur); }
    public void AjouterProposition(Proposition uneProposition) {base_Utilisateur.AjouterProposition(uneProposition);}
    public void AjouterReponse(Repondre uneReponse) {base_Utilisateur.AjouterRepondre(uneReponse);}

    public void MettreAJourParticipe (Participe uneParticipation) { base_Utilisateur.MettreAJourParticipe(uneParticipation);}

    public ArrayList<Festivals> getLesFestivals() { return base_Utilisateur.getLesFestivals(); }

    public ArrayList<Participe> getLesParticipationUtilisateur(int idUtilisateur) { return base_Utilisateur.getLesParticipationUtilisateur(idUtilisateur); }

    public ArrayList<Concours> getLesConcoursFini() {
        ArrayList<Concours> lesConcoursFini = base_Utilisateur.getLesConcours();
        Date now = new Date();
        ArrayList <Concours> lesConcoursRetenu = new ArrayList<Concours>();
        for(Concours unConcour : lesConcoursFini)
        {
            if(now.after(unConcour.getDateFin()))
            {
                    lesConcoursRetenu.add(unConcour);
            }
        }
        return lesConcoursRetenu;
    }
    public ArrayList<Concours> getLesConcoursEnCours(int idUtilisateur) {
        ArrayList<Concours> lesConcours = new ArrayList<>();
        lesConcours = base_Utilisateur.getLesConcours(idUtilisateur);

        Date now = new Date();
        ArrayList <Concours> lesConcoursRetenu = new ArrayList<Concours>();
        for(Concours unConcour : lesConcours)
        {
            if(now.compareTo(unConcour.getDateDebut()) >= 0)
            {
                if(now.compareTo(unConcour.getDateFin()) <= 0)
                {
                    lesConcoursRetenu.add(unConcour);
                }
            }
        }
        return lesConcoursRetenu;
    }
    public ArrayList<Participe> getLesClassementTrie(int idConcour) {
        ArrayList<Participe> lesParticipation = new ArrayList<>();
        lesParticipation = base_Utilisateur.getLesParticipationConcours(idConcour);
        Collections.sort(lesParticipation, new Comparator<Participe>() {
            @Override
            public int compare(Participe o1, Participe o2)
            {
                if (o1.getScore() > o2.getScore())
                {
                    return -1;
                }
                else if (o1.getScore() < o2.getScore())
                {
                    return 1;
                }
                else if (o1.getScore() == o2.getScore());
                {
                    return o1.getTemps().compareTo(o2.getTemps());
                }
            }
        });
        return lesParticipation;
    }
    public ArrayList<Concours> getLesConcours() { return base_Utilisateur.getLesConcours(); }

    public ArrayList<Questions> getLesQuestion(int idConcours) { return base_Utilisateur.getLesQuestion(idConcours); }
    public ArrayList<Questions> getLesQuestionStats(int idConcours) { return base_Utilisateur.getLesQuestionStats(idConcours); }

    public ArrayList<Utilisateurs> getLesUtilisateursAdmin() { return base_Utilisateur.getLesUtilisateursAdmin(); }
    public Utilisateurs getUtilisateur(String qpseudo, String qmotPasse) { return base_Utilisateur.Connexion(qpseudo, qmotPasse); }
    public int getQuestionId(int numQuestion) { return ensQuestions.get(numQuestion).getId();}
    public Concours getLeConcours(int idConcours){return  base_Utilisateur.getLeConcour(idConcours);}

    public ArrayList<Concours> getLesConcours(int idUtilisateur) { return base_Utilisateur.getLesConcours(idUtilisateur); }
    public ArrayList<Utilisateurs> getLesUtilisateurs() { return base_Utilisateur.getLesUtilisateurs(); }
    public ArrayList<Participe> getLesParticipation() { return base_Utilisateur.getLesParticipation(); }
    public ArrayList<Participe> getLesParticipationConcours(int idConcour) { return base_Utilisateur.getLesParticipationConcours(idConcour); }

    public int getDernierIdConcour() {return base_Utilisateur.getDernierIdConcour(); }
    public int getDernierIdQuestion() {return base_Utilisateur.getDernierIdQuestions(); }
    public int getDernierIdProposition() {return base_Utilisateur.getDernierIdProposition(); }

    public void supprimerFestival(int idFestival) { base_Utilisateur.supprimerFestival(idFestival); }
    public void supprimerUtilisateur(int idUtilisateur) { base_Utilisateur.supprimerUtilisateur(idUtilisateur); }
    public void supprimerConcour(int idUtilisateur) { base_Utilisateur.supprimerConcour(idUtilisateur); }

    public void modifConcours(Concours unConcours){base_Utilisateur.modifConcours(unConcours);}
    public void modifQuestion(Questions uneQuestion){base_Utilisateur.modifQuestion(uneQuestion);}
    public void modifProposition(Proposition uneProposition){base_Utilisateur.modifProposition(uneProposition);}
    public void modifReponse(Repondre uneReponse){base_Utilisateur.modifReponse(uneReponse);}



    public int getNbReponseValide(int idUtilisateur, int idConcour) { return base_Utilisateur.getNbReponseValide(idUtilisateur, idConcour);}

    //public String getReponse(int numQuestion) { return ensQuestions.get(numQuestion).getSolution();}
    //public String getProposition (int num_question, int num_proposition) { return ensQuestions.get(num_question).getPropositions(num_proposition-1);}
    //public String getQuestion(int numQuestion) { return ensQuestions.get(numQuestion).getIntitule();}

    public String getReponse(int numQuestion)
    {
        String reponse = "erreur";
        for (Repondre uneReponse : ensQuestions.get(numQuestion).getListeReponse())
        {
         if (uneReponse.getReponse() == true)
         {
             reponse = uneReponse.getUneProposition().getIntitule();
         }
        }
        return reponse;
    }
    public String getProposition (int num_question, int num_proposition)
    { return ensQuestions.get(num_question).getListeReponse().get(num_proposition).getUneProposition().getIntitule(); }

    public String getQuestion(int numQuestion)
    { return ensQuestions.get(numQuestion).getTitre();}

    public ArrayList<Questions> LesQuestionsModif()
    {return ensQuestions;}

    public ArrayList<Integer> getNbQuestionNbReponse(int idConcours)
    {Log.e("bonjour", "bonjour"); return base_Utilisateur.getNbQuestionNbReponse(idConcours);}


    public void test()
    {
        int i = 0;
        Log.e("tagTestNb", String.valueOf(ensQuestions.size()));
        for (Questions uneQuestions :ensQuestions)
        {
            Log.e("tagTest", String.valueOf(ensQuestions.get(i).getId()));
            Log.e("tagTest", String.valueOf(ensQuestions.get(i).getTitre()));
            for(Repondre uneReponse : uneQuestions.getListeReponse())
            {
                Log.e("reponse :", uneReponse.getUneProposition().getIntitule());
                if(uneReponse.getReponse() == true)
                {
                    Log.e("reponseQuestion :", uneReponse.getUneProposition().getIntitule());
                }
            }

            i++;
        }
    }
}
