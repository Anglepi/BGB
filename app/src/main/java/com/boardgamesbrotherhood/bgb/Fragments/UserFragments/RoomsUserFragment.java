package com.boardgamesbrotherhood.bgb.Fragments.UserFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.R;


public class RoomsUserFragment extends Fragment {

    public RoomsUserFragment() {
        // Required empty public constructor
    }

    public static RoomsUserFragment newInstance(String param1, String param2) {
        RoomsUserFragment fragment = new RoomsUserFragment();
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
        return inflater.inflate(R.layout.fragment_rooms_user, container, false);
    }
}