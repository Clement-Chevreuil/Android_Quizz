package com.example.monapplication.UserAdapter;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.example.monapplication.R;
import com.example.monapplication.Models.Participe;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClassementAdapter extends BaseAdapter {
    private Context context;
    private List<Participe> ParticipationList;
    private LayoutInflater inflater;

    public ClassementAdapter(Context context, List<Participe> ParticipationList)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.adapter_score2, null);
        //get information about item
        Participe uneParticipation = getItem(i);

        String itemName = uneParticipation.getUnUtilisateur().getPseudo();

        SimpleDateFormat dates = new SimpleDateFormat("HH:mm:ss");
        Date itemTemps = uneParticipation.getTemps();
        String diff = dates.format(itemTemps);
        int itemScore = uneParticipation.getScore();

        //get item name view
        TextView concourNom = view.findViewById(R.id.item1);
        concourNom.setText(itemName);

        String itemScoreConvertie = String.valueOf(itemScore);
        TextView concourScore = view.findViewById(R.id.item2);
        concourScore.setText("Score : "+itemScoreConvertie);

        TextView conourTemps = view.findViewById(R.id.item3);
        conourTemps.setText("Temps : "+diff);

        return view;
    }
}
