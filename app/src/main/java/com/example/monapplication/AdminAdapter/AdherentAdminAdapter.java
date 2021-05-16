package com.example.monapplication.AdminAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.monapplication.R;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Utilisateurs;

import java.util.List;

public class AdherentAdminAdapter extends BaseAdapter {
    private Context context;
    private List<Utilisateurs> festivalList;
    private LayoutInflater inflater;
    private final int code_fenetre = 20;
    private int unNumQ = 0;
    private int idUtilisateur;
    private ensemble commandeSup = new ensemble();

    public AdherentAdminAdapter(Context context, List<Utilisateurs> festivalList)
    {
        this.context = context;
        this.festivalList = festivalList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return festivalList.size();
    }

    @Override
    public Object getItem(int position) {
        return festivalList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.adapter_item_festival_admin, null);
        Utilisateurs currenItem = (Utilisateurs) getItem(i);

        String itemName = currenItem.getNom();
        String itemPrenom = currenItem.getPrenom();
        final int itemId = currenItem.getId();

        //get item name view



        TextView itemNameView = view.findViewById(R.id.item_festival_name);
        itemNameView.setText(itemName+" "+itemPrenom);

        ImageButton sup = view.findViewById(R.id.supprimer);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandeSup.creationBdd_utilisateur(context);
                commandeSup.supprimerUtilisateur(itemId);
                ((Activity) context) .recreate();

            }
        });
        return view;
    }
}
