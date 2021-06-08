package com.boardgamesbrotherhood.bgb.Fragments.GameFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boardgamesbrotherhood.bgb.Activities.GameActivity;
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
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Glide.with(this)
                .load(GameActivity.game.getCardThumbnail())
                .into(gameImage);

        return view;

    }
}