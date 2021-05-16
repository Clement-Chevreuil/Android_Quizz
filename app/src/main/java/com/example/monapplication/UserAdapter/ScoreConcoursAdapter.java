package com.example.monapplication.UserAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.monapplication.R;
import com.example.monapplication.User.activityClassement;
import com.example.monapplication.Models.Participe;
import java.util.List;

public class ScoreConcoursAdapter extends BaseAdapter {
    private Context context;
    private List<Participe> ParticipationList;
    private LayoutInflater inflater;

    public ScoreConcoursAdapter(Context context, List<Participe> ParticipationList)
    {
        this.context = context;
        this.ParticipationList = ParticipationList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ParticipationList.size();
    }

    @Override
    public Participe getItem(int position) {
        return ParticipationList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.adapter_score2, null);

        Participe uneParticipation = getItem(i);
        String festivalNom = uneParticipation.getUnConcour().getUnFestival().getNom();
        String concourNom = uneParticipation.getUnConcour().getNom();
        int scoreUtilisateur = uneParticipation.getScore();
        final int itemIdConcour = uneParticipation.getUnConcour().getId();
        final int itemIdUtilisateur= uneParticipation.getUnUtilisateur().getId();

        //get item name view
        TextView itemNameView = view.findViewById(R.id.item1);
        itemNameView.setText("Festival : "+festivalNom);

        //get item price view
        TextView itemPriceView = view.findViewById(R.id.item2);
        itemPriceView.setText(concourNom);

        //get item price view
        TextView itemScoreView = view.findViewById(R.id.item3);
        itemScoreView.setText("Score : "+scoreUtilisateur);


        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                Intent unIntent = new Intent(context, activityClassement.class);
                unIntent.putExtra("idConcour", itemIdConcour);
                unIntent.putExtra("idUtilisateur", itemIdUtilisateur);
                context.getApplicationContext().startActivity(unIntent);
            }
        });

        return view;
    }
}
