package com.boardgamesbrotherhood.bgb.Fragments.UserFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;
import com.google.android.material.snackbar.Snackbar;


public class ProfileUserFragment extends Fragment {

    public ProfileUserFragment() {
        // Required empty public constructor
    }

    public static ProfileUserFragment newInstance(String param1, String param2) {
        ProfileUserFragment fragment = new ProfileUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile_user, container, false);

        EditText name = view.findViewById(R.id.editTextPersonName);
        EditText last_name = view.findViewById(R.id.editTextPersonLastName);
        Button btnSave = view.findViewById(R.id.btnSaveProfile);

        name.setText(UserSession.user.getFirstName());
        last_name.setText(UserSession.user.getLastName());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession.user.setFirstName(name.getText().toString());
                UserSession.user.setLastName(last_name.getText().toString());
                FirebaseProvider.updateExistingUser(UserSession.user);
                Snackbar.make(view, "Perfil actualizado con exito", Snackbar.LENGTH_LONG).show();
            }
        });


        return view;
    }
}