package com.example.monapplication.AdminAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.monapplication.Admin.activityConcourAdminModif;
import com.example.monapplication.R;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import com.example.monapplication.User.activityClassement;

import java.util.List;

public class ConcoursAdminAdapter extends BaseAdapter {
    private Context context;
    private List<Concours> concoursList;
    private LayoutInflater inflater;
    private final int code_fenetre = 20;
    private int unNumQ = 0;
    private int idUtilisateur;

    public ConcoursAdminAdapter(Context context, List<Concours> concoursList)
    {
        this.context = context;
        this.concoursList = concoursList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return concoursList.size();
    }

    @Override
    public Object getItem(int position) {
        return concoursList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.adapter_item_festival_admin, null);
        Concours currenItem = (Concours) getItem(i);

        String itemName = currenItem.getNom();
        final int itemId = currenItem.getId();

        //get item name view

        TextView itemNameView = view.findViewById(R.id.item_festival_name);
        itemNameView.setText(itemName);

        ImageButton sup = view.findViewById(R.id.supprimer);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ensemble commandeSup = new ensemble();
                commandeSup.creationBdd_concours(context);
                commandeSup.supprimerConcour(itemId);
                ((Activity) context) .recreate();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Intent unIntent = new Intent(context, activityConcourAdminModif.class);
                unIntent.putExtra("idConcour", itemId);
                context.getApplicationContext().startActivity(unIntent);
            }
        });
        return view;
    }
}
