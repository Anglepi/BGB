package com.boardgamesbrotherhood.bgb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.boardgamesbrotherhood.bgb.CardDisplayable;
import com.boardgamesbrotherhood.bgb.ExtendedCardDisplayable;
import com.boardgamesbrotherhood.bgb.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ExtendedCardsAdapter extends RecyclerView.Adapter<ExtendedCardsAdapter.MyViewHolder> {
    private Context context;
    private List<? extends ExtendedCardDisplayable> cardList;

    public ExtendedCardsAdapter(Context context, List<? extends ExtendedCardDisplayable> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public TextView description;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.extended_card_title);
            thumbnail = view.findViewById(R.id.extended_card_thumbnail);
            description = view.findViewById(R.id.extended_card_description);

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
                .inflate(R.layout.extended_card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ExtendedCardDisplayable displayable = cardList.get(position);
        holder.title.setText(displayable.getCardTitle());
        holder.description.setText(displayable.getCardDescription());

        Glide.with(context)
                .load(displayable.getCardThumbnail())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}