package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.Category;
import com.boardgamesbrotherhood.bgb.Models.Company;
import com.boardgamesbrotherhood.bgb.Models.Establishment;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.fragments.CategoriesFragment;
import com.boardgamesbrotherhood.bgb.fragments.CompaniesFragment;
import com.boardgamesbrotherhood.bgb.fragments.EstablishmentsFragment;
import com.boardgamesbrotherhood.bgb.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment, categoriesFragment, establishmentsFragment, companiesFragment;
    public static List<Game> popularGames;
    public static List<Category> categories;
    public static List<Establishment> establishments;
    public static List<Company> companies;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_inicio);
        setSupportActionBar(toolbar);


        homeFragment = new HomeFragment();
        categoriesFragment = new CategoriesFragment();
        establishmentsFragment = new EstablishmentsFragment();
        companiesFragment = new CompaniesFragment();

        loadFragment(homeFragment);

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
                    /*case R.id.action_companies:
                        loadFragment(companiesFragment);
                        toolbar.setTitle(R.string.menu_companies);
                        return true;*/
                }
                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(UserSession.SessionEstablished){
            getMenuInflater().inflate(R.menu.toolbar_menu_logged_user, menu);
        } else {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.search_button:
                //TODO boton de la lupa
                break;
            case R.id.overflow_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.overflow_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.overflow_my_rofile:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","profile");
                startActivity(intent);
                break;
            case R.id.overflow_my_games:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","games");
                startActivity(intent);
                break;
            case R.id.overflow_ratings:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","ratings");
                startActivity(intent);
                break;
            case R.id.overflow_my_rooms:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","rooms");
                startActivity(intent);
                break;
            case R.id.overflow_logout:
                UserSession.Logout();
                finish();
                startActivity(getIntent());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}