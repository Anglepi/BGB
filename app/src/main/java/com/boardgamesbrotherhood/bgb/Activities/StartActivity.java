package com.boardgamesbrotherhood.bgb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.Models.Category;
import com.boardgamesbrotherhood.bgb.Models.Establishment;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.OnDataLoaded;
import com.boardgamesbrotherhood.bgb.R;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private int nLoads = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dataInitializer();
    }

    public void dataInitializer(){
        MainActivity.popularGames = new ArrayList<>();
        FirebaseProvider.loadGames(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> games) {
                MainActivity.popularGames.addAll((List<Game>) games);
                dataInitializingFinished();
            }
        });

        MainActivity.categories = new ArrayList<>();
        FirebaseProvider.loadCategories(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> ctg) {
                MainActivity.categories.addAll((List<Category>) ctg);
                dataInitializingFinished();
            }
        });

        MainActivity.establishments = new ArrayList<>();
        FirebaseProvider.loadEstablishments(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> est) {
                MainActivity.establishments.addAll((List<Establishment>) est);
                dataInitializingFinished();
            }
        });

        /*MainActivity.companies = new ArrayList<>();
        FirebaseProvider.loadCompanies(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> comp) {
                MainActivity.companies.addAll((List<CardDisplayable>) comp);
                dataInitializingFinished();
            }
        });*/
    }

    public synchronized void dataInitializingFinished(){
        nLoads--;
        if(nLoads==0) startMainActivity();
    }

    public void startMainActivity(){
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }


}