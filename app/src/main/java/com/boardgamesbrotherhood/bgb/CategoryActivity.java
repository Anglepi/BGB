package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Adapters.ExtendedCardsAdapter;
import com.boardgamesbrotherhood.bgb.Models.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity {
    private String category;
    private ArrayList<Game> gamesInCategory;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        category = getIntent().getExtras().getString("category");

        initializeGames();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeGames(){
        gamesInCategory = new ArrayList<>();
        for(Game game: MainActivity.popularGames){
            if(game.getCategories().contains(category)){
                gamesInCategory.add(game);
            }
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_category);
        ExtendedCardsAdapter eca = new ExtendedCardsAdapter(this,gamesInCategory);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(eca);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Buscar la lupa y ponerle el evento

        return super.onOptionsItemSelected(item);
    }
}