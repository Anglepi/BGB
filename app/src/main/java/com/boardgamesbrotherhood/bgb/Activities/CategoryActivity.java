package com.boardgamesbrotherhood.bgb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Adapters.ExtendedCardsAdapter;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;

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