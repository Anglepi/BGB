package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.fragments.CategoriesFragment;
import com.boardgamesbrotherhood.bgb.fragments.CompaniesFragment;
import com.boardgamesbrotherhood.bgb.fragments.EstablishmentsFragment;
import com.boardgamesbrotherhood.bgb.fragments.HomeFragment;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fa = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        FirebaseProvider.loadGames(new OnGamesLoaded() {
            @Override
            public void onTaskComplete(List<Game> games) {
                //Aqui usar games para hacer la vista
                ImageView v = findViewById(R.id.imagetest);
                Glide.with(getApplicationContext()).load(games.get(0).getThumbnail()).into(v);
            }
        });
*/
/*
TODO revisar creacion usuario

        fa.signInWithEmailAndPassword("esdfe@hotmail.com", "contraseña").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                                                                     @Override
                                                                                                     public void onSuccess(AuthResult authResult) {
                                                                                                         Snackbar.make(getWindow().getDecorView().getRootView(), "bien", Snackbar.LENGTH_LONG).show();
                                                                                                     }
                                                                                                 }
        );

        fa.createUserWithEmailAndPassword("test@test.com", "clavetest");
*/
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.menu_inicio);

        loadFragment(new HomeFragment());

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        toolbar.setTitle(R.string.menu_inicio);
                        return true;
                    case R.id.action_categories:
                        fragment = new CategoriesFragment();
                        loadFragment(fragment);
                        toolbar.setTitle(R.string.menu_categories);
                        return true;
                    case R.id.action_establishments:
                        fragment = new EstablishmentsFragment();
                        loadFragment(fragment);
                        toolbar.setTitle(R.string.menu_establishments);
                        return true;
                    case R.id.action_companies:
                        fragment = new CompaniesFragment();
                        loadFragment(fragment);
                        toolbar.setTitle(R.string.menu_companies);
                        return true;
                }
                return false;
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}