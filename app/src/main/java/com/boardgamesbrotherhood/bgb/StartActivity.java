package com.boardgamesbrotherhood.bgb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private int nLoads = 4;

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
                MainActivity.popularGames.addAll((List<CardDisplayable>) games);
                dataInitializingFinished();
            }
        });

        MainActivity.categories = new ArrayList<>();
        FirebaseProvider.loadCategories(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> ctg) {
                MainActivity.categories.addAll((List<CardDisplayable>) ctg);
                dataInitializingFinished();
            }
        });

        MainActivity.establishments = new ArrayList<>();
        FirebaseProvider.loadEstablishments(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> est) {
                MainActivity.establishments.addAll((List<CardDisplayable>) est);
                dataInitializingFinished();
            }
        });

        MainActivity.companies = new ArrayList<>();
        FirebaseProvider.loadCompanies(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> comp) {
                MainActivity.companies.addAll((List<CardDisplayable>) comp);
                dataInitializingFinished();
            }
        });
    }

    public synchronized void dataInitializingFinished(){
        nLoads--;
        if(nLoads==0) startMainActivity();
    }

    public void startMainActivity(){
        Intent toMainActivity = new Intent(this, MainActivity.class);
        startActivity(toMainActivity);
    }


}