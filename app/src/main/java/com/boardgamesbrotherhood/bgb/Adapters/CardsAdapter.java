package com.boardgamesbrotherhood.bgb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.boardgamesbrotherhood.bgb.CardDisplayable;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {
    private Context context;
    private List<? extends CardDisplayable> cardList;
    private List<? extends CardDisplayable> originalCardList;

    public CardsAdapter(Context context, List<? extends CardDisplayable> cardList) {
        this.context = context;
        this.cardList = cardList;
        this.originalCardList = cardList;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.card_title);
            thumbnail = view.findViewById(R.id.card_thumbnail);

            //Esto probablemente dará problemas si añadimos nuevos elementos a la lista en tiempo de ejecución
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CardDisplayable selected = cardList.get(getAbsoluteAdapterPosition());
                    selected.openCard(context);
                    //Pasar contexto como parametro a opencard para hacer el intent
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CardDisplayable displayable = cardList.get(position);
        holder.title.setText(displayable.getCardTitle());

        Glide.with(context)
                .load(displayable.getCardThumbnail())
                .into(holder.thumbnail);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayable.openCard();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults fr = new FilterResults();

            if(constraint.length()!=0 && constraint != null){
                List<CardDisplayable> filtered = new ArrayList<>();

                for(int i=0 ; i<originalCardList.size() ; i++){
                    String query = constraint.toString().toLowerCase();
                    String cardTitle = originalCardList.get(i).getCardTitle().toLowerCase();
                    if(cardTitle.contains(query)){
                        filtered.add(originalCardList.get(i));
                    }
                }

                fr.count = filtered.size();
                fr.values = filtered;
            } else {
                fr.count = originalCardList.size();
                fr.values = originalCardList;
            }

            return fr;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cardList = (List<? extends CardDisplayable>) results.values;
            notifyDataSetChanged();
        }
    };

    public void filterByTitle(String newText) {
        filter.filter(newText);
    }
}