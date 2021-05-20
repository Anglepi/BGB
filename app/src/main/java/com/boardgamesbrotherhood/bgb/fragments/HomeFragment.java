package com.boardgamesbrotherhood.bgb.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.Adapters.GamesAdapter;
import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.OnGamesLoaded;
import com.boardgamesbrotherhood.bgb.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    List<Game> gamesList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);
        GamesAdapter ga = new GamesAdapter(getActivity(), gamesList);
        rv.setAdapter(ga);

        FirebaseProvider.loadGames(new OnGamesLoaded() {
            @Override
            public void onTaskComplete(List<Game> games) {
                gamesList.clear();
                gamesList.addAll(games);
                ga.notifyDataSetChanged();
                System.out.println("donete");
            }
        });
        return view;
    }
}