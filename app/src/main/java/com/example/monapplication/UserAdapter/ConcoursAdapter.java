package com.example.monapplication.UserAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.monapplication.R;
import com.example.monapplication.User.activityQuestion;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import com.example.monapplication.Models.Participe;
import com.example.monapplication.Models.Utilisateurs;
import java.util.Date;
import java.util.List;

public class ConcoursAdapter extends BaseAdapter {
    private Context context;
    private List<Concours> concourList;
    private LayoutInflater inflater;
    private int idUtilisateur;

    public ConcoursAdapter(Context context, List<Concours> concourList, int idUtilisateur)
    {
        this.context = context;
        this.concourList = concourList;
        this.inflater = LayoutInflater.from(context);
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int getCount() {
        return concourList.size();
    }

    @Override
    public Object getItem(int position) {
        return concourList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, final ViewGroup parent) {
        view = inflater.inflate(R.layout.adapter_score2, null);
        Concours unConcour = (Concours) getItem(i);
        String concourNom = unConcour.getNom();
        String festivalNom = unConcour.getUnFestival().getNom();
        String concourRec = unConcour.getRecompence();
        final int idConcour = unConcour.getId();


        //get item icon view
//        ImageView itemIconView = view.findViewById(R.id.item_icon);
        // int resId = context.getResources().getIdentifier();
        // itemIconView.setImageResource(resId);

        //get name concour view
        TextView itemNameView = view.findViewById(R.id.item1);
        itemNameView.setText(concourNom);
        //get name festival view
        TextView itemPriceView = view.findViewById(R.id.item2);
        itemPriceView.setText("Festival : "+festivalNom);

        TextView itemPriceView2 = view.findViewById(R.id.item3);
        itemPriceView2.setText("Recompense : "+concourRec);

        view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            public void onClick(View view)
            {

                ensemble ensemble = new ensemble();
                ensemble.creationBdd_festival(context);
                Concours unConcour = new Concours(idConcour);
                Utilisateurs unUtilisateur = new Utilisateurs(idUtilisateur);
                Date currentTime = new Date();
                Participe uneParticipation = new Participe(unConcour, unUtilisateur, currentTime );
                ensemble.AjouterParticipe(uneParticipation);

                Intent unIntent = new Intent(context, activityQuestion.class);
                unIntent.putExtra("idConcour", idConcour);
                unIntent.putExtra("idUtilisateur", idUtilisateur);
                context.getApplicationContext().startActivity(unIntent);
                ((Activity) context) .recreate();
            }
        });

        return view;
    }
}
