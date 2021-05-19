package com.boardgamesbrotherhood.bgb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewHolder> {
    private Context context;
    private List<Game> gameList;

    public GamesAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
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
        System.out.println("Creando "+position);

        final Game game = gameList.get(position);
        holder.title.setText(game.getTitle());

        Glide.with(context)
                .load(game.getThumbnail())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}