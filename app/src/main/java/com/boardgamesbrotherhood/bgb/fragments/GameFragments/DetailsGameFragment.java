package com.boardgamesbrotherhood.bgb.fragments.GameFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.R;


public class DetailsGameFragment extends Fragment {

    public DetailsGameFragment() {
        // Required empty public constructor
    }

    public static DetailsGameFragment newInstance(String param1, String param2) {
        DetailsGameFragment fragment = new DetailsGameFragment();
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
        return inflater.inflate(R.layout.fragment_details_game, container, false);
    }
}