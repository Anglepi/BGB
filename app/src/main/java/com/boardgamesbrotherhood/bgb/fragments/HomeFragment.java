package com.boardgamesbrotherhood.bgb.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boardgamesbrotherhood.bgb.Adapters.GamesAdapter;
import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.OnGamesLoaded;
import com.boardgamesbrotherhood.bgb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        gamesList.add(new Game("TESTO", "https://i.ebayimg.com/thumbs/images/g/3hcAAOSwCH9eTf1G/s-l96.jpg"));
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);
        GamesAdapter ga = new GamesAdapter(getActivity(), gamesList);
        rv.setAdapter(ga);

        TextView t = view.findViewById(R.id.textidprueba);
        t.setText("PRUEBAAAAAAAAAAAAAAAAAAAAA");


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