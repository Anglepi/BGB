package com.boardgamesbrotherhood.bgb.Fragments.GameFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.R;


public class RoomsGameFragment extends Fragment {

    public RoomsGameFragment() {
        // Required empty public constructor
    }


    public static RoomsGameFragment newInstance(String param1, String param2) {
        RoomsGameFragment fragment = new RoomsGameFragment();
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
        return inflater.inflate(R.layout.fragment_rooms_game, container, false);
    }
}