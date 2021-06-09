package com.boardgamesbrotherhood.bgb.Fragments.UserFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boardgamesbrotherhood.bgb.Activities.MainActivity;
import com.boardgamesbrotherhood.bgb.Adapters.CardsAdapter;
import com.boardgamesbrotherhood.bgb.Adapters.ExtendedCardsAdapter;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;

import java.util.ArrayList;
import java.util.List;


public class GamesUserFragment extends Fragment {
    List<Game> user_games = new ArrayList<>();
    ExtendedCardsAdapter ega;

    public GamesUserFragment() {
        // Required empty public constructor
    }


    public static GamesUserFragment newInstance() {
        GamesUserFragment fragment = new GamesUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateGames();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games_user, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_user_games);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);

        ega = new ExtendedCardsAdapter(getActivity(), user_games);
        rv.setAdapter(ega);
        return view;
    }

    public void populateGames(){
        user_games.clear();
        ArrayList<String> user_liked_games = UserSession.user.getUserFavGames();
        for (Game gm : MainActivity.popularGames) {
            if(user_liked_games.contains(gm.getTitle())){
                user_games.add(gm);
            }
        }
    }

    public void notifyDatasetChanged(){
        populateGames();
        ega.notifyDataSetChanged();
    }
}