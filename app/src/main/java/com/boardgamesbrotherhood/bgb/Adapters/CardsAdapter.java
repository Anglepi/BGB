package com.boardgamesbrotherhood.bgb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.boardgamesbrotherhood.bgb.CardDisplayable;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {
    private Context context;
    private List<CardDisplayable> cardList;

    public CardsAdapter(Context context, List<CardDisplayable> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ImageView thumbnail;
        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);
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
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}