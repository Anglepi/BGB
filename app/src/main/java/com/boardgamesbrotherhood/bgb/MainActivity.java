package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.menu_inicio);

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.action_home:
                        //TODO something
                        toolbar.setTitle(R.string.menu_inicio);
                        return true;
                    case R.id.action_categories:
                        //TODO something
                        toolbar.setTitle(R.string.menu_categories);
                        return true;
                    case R.id.action_establishments:
                        //TODO something
                        toolbar.setTitle(R.string.menu_establishments);
                        return true;
                    case R.id.action_companies:
                        //TODO something
                        toolbar.setTitle(R.string.menu_companies);
                        return true;
                }
                return false;
            }
        });


    }
}