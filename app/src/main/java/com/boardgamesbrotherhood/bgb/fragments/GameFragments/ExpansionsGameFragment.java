package com.boardgamesbrotherhood.bgb.fragments.GameFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.R;


public class ExpansionsGameFragment extends Fragment {


    public ExpansionsGameFragment() {
        // Required empty public constructor
    }


    public static ExpansionsGameFragment newInstance(String param1, String param2) {
        ExpansionsGameFragment fragment = new ExpansionsGameFragment();
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
        return inflater.inflate(R.layout.fragment_expansions_game, container, false);
    }
}