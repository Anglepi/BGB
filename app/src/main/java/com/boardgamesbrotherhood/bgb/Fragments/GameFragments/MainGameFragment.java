package com.boardgamesbrotherhood.bgb.Fragments.GameFragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boardgamesbrotherhood.bgb.Activities.GameActivity;
import com.boardgamesbrotherhood.bgb.Activities.LoginActivity;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;
import com.bumptech.glide.Glide;


public class MainGameFragment extends Fragment {

    public MainGameFragment() {
        // Required empty public constructor
    }

    public static MainGameFragment newInstance() {
        MainGameFragment fragment = new MainGameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_game, container, false);

        TextView gameDescription = view.findViewById(R.id.GameDescription);
        gameDescription.setText(GameActivity.game.getDescription());

        ImageView gameImage = view.findViewById(R.id.GameImage);

        Button btnRooms = view.findViewById(R.id.GameRoomsButton);
        btnRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GameActivity)getActivity()).loadFragment("roomsFragment");
            }
        });

        Button btnDetails = view.findViewById(R.id.GameDetailsButton);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GameActivity)getActivity()).loadFragment("detailsFragment");
            }
        });

        Button btnExpansions = view.findViewById(R.id.GameExpansionsButton);
        btnExpansions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GameActivity)getActivity()).loadFragment("expansionsFragment");
            }
        });

        Button btnLike = view.findViewById(R.id.GameLikeButton);
        if(UserSession.user.likesGame(GameActivity.game.getTitle())){
            btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_star_24,0,0,0);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserSession.SessionEstablished){
                    UserSession.user.addOrRemoveLikedGame(GameActivity.game.getTitle());
                    if(UserSession.user.likesGame(GameActivity.game.getTitle())){
                        btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_star_24,0,0,0);
                    } else {
                        btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_star_outline_24,0,0,0);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        Glide.with(this)
                .load(GameActivity.game.getCardThumbnail())
                .into(gameImage);

        return view;

    }
}