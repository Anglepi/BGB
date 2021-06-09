package com.boardgamesbrotherhood.bgb.Fragments;

import android.app.SearchManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boardgamesbrotherhood.bgb.Adapters.CardsAdapter;
import com.boardgamesbrotherhood.bgb.Activities.MainActivity;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;

public class HomeFragment extends Fragment {
    CardsAdapter ga;
    View fragmentView;

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
        ga = new CardsAdapter(getActivity(), MainActivity.popularGames);
        rv.setAdapter(ga);
        setHasOptionsMenu(true);
        fragmentView = view;
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if(UserSession.SessionEstablished){
            inflater.inflate(R.menu.toolbar_menu_logged_user, menu);
        } else {
            inflater.inflate(R.menu.toolbar_menu, menu);
        }

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_button).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null){
                    ga.filterByTitle(newText);
                }
                if(!newText.isEmpty()){
                    ImageView logo = fragmentView.findViewById(R.id.logoBGB);
                    TextView texto = fragmentView.findViewById(R.id.title_popular_games);
                    logo.setVisibility(View.GONE);
                    texto.setText(R.string.search_results);
                } else {
                    ImageView logo = fragmentView.findViewById(R.id.logoBGB);
                    TextView texto = fragmentView.findViewById(R.id.title_popular_games);
                    logo.setVisibility(View.VISIBLE);
                    texto.setText(R.string.popular_games);
                }
                return false;
            }
        });
    }
}