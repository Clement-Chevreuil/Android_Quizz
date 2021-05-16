package com.example.monapplication.AdminAdapter;
        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.example.monapplication.R;
        import com.example.monapplication.GestionBdd.ensemble;
        import com.example.monapplication.Models.Festivals;
        import java.util.List;

public class FestivalAdminAdapter extends BaseAdapter {
    private Context context;
    private List<Festivals> festivalList;
    private LayoutInflater inflater;

    public FestivalAdminAdapter(Context context, List<Festivals> festivalList)
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
        Festivals currenItem = (Festivals) getItem(i);

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
                commandeSup.creationBdd_festival(context);
                commandeSup.supprimerFestival(itemId);
                Toast.makeText(context, "Festival supprimé.", Toast.LENGTH_SHORT).show();
                ((Activity) context) .recreate();
            }
        });
        return view;
    }
}
