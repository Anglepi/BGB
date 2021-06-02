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
    Fragment homeFragment, categoriesFragment, establishmentsFragment, companiesFragment;
    public static List<Game> popularGames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        homeFragment = new HomeFragment();
        categoriesFragment = new CategoriesFragment();
        establishmentsFragment = new EstablishmentsFragment();
        companiesFragment = new CompaniesFragment();

        dataInitializer();

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        loadFragment(homeFragment);
                        toolbar.setTitle(R.string.menu_inicio);
                        return true;
                    case R.id.action_categories:
                        loadFragment(categoriesFragment);
                        toolbar.setTitle(R.string.menu_categories);
                        return true;
                    case R.id.action_establishments:
                        loadFragment(establishmentsFragment);
                        toolbar.setTitle(R.string.menu_establishments);
                        return true;
                    case R.id.action_companies:
                        loadFragment(companiesFragment);
                        toolbar.setTitle(R.string.menu_companies);
                        return true;
                }
                return false;
            }
        });


    }

    private void dataInitializer(){
        popularGames = new ArrayList<>();
        FirebaseProvider.loadGames(new OnGamesLoaded() {
            @Override
            public void onTaskComplete(List<Game> games) {
                popularGames.clear();
                popularGames.addAll(games);
                loadFragment(homeFragment);
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