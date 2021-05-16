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
import com.example.monapplication.R;
import com.example.monapplication.User.activityClassement;
import com.example.monapplication.GestionBdd.ensemble;
import com.example.monapplication.Models.Concours;
import java.util.List;

public class ConcoursFiniAdminAdapter extends BaseAdapter {
    private Context context;
    private List<Concours> concoursList;
    private LayoutInflater inflater;
    private final int code_fenetre = 20;
    private int unNumQ = 0;
    private int idUtilisateur;

    public ConcoursFiniAdminAdapter(Context context, List<Concours> concoursList)
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

        view = inflater.inflate(R.layout.adapter_item, null);
        Concours currenItem = (Concours) getItem(i);

        String itemName = currenItem.getNom();
        String itemName2 = currenItem.getUnFestival().getNom();
        final int itemId = currenItem.getId();
        int envoieIdConcour = currenItem.getId();
        final int itemIdConcour = envoieIdConcour;

        //get item name view

        TextView itemNameView = view.findViewById(R.id.concourNom);
        itemNameView.setText(itemName);

        TextView itemNameViewFestival = view.findViewById(R.id.festivalNom);
        itemNameViewFestival.setText("Festival : "+itemName2);


        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Intent unIntent = new Intent(context, activityClassement.class);
                unIntent.putExtra("idConcour", itemIdConcour);
                context.getApplicationContext().startActivity(unIntent);
            }
        });
        return view;
    }
}
