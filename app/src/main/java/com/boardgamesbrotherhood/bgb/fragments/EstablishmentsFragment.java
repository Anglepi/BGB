package com.boardgamesbrotherhood.bgb.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boardgamesbrotherhood.bgb.Adapters.CardsAdapter;
import com.boardgamesbrotherhood.bgb.MainActivity;
import com.boardgamesbrotherhood.bgb.R;


public class EstablishmentsFragment extends Fragment {

    public EstablishmentsFragment() {
        // Required empty public constructor
    }

    public static EstablishmentsFragment newInstance() {
        EstablishmentsFragment fragment = new EstablishmentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_establishments, container, false);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);
        CardsAdapter ga = new CardsAdapter(getActivity(), MainActivity.establishments);
        rv.setAdapter(ga);

        return view;
    }
}