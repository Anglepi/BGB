package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.fragments.CategoriesFragment;
import com.boardgamesbrotherhood.bgb.fragments.CompaniesFragment;
import com.boardgamesbrotherhood.bgb.fragments.EstablishmentsFragment;
import com.boardgamesbrotherhood.bgb.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.menu_inicio);

        loadFragment(new HomeFragment());

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
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

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}