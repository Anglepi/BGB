package com.boardgamesbrotherhood.bgb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;
import com.boardgamesbrotherhood.bgb.Fragments.GameFragments.*;

public class GameActivity extends AppCompatActivity {
    public static Game game;
    private Fragment mainFragment, roomsFragment, detailsFragment, expansionsFragment;
    private static GameActivity instance;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getIntent().getExtras().getParcelable("game");
        setContentView(R.layout.activity_game);

        mainFragment = new MainGameFragment();
        roomsFragment = new RoomsGameFragment();
        detailsFragment = new DetailsGameFragment();
        expansionsFragment = new ExpansionsGameFragment();

        loadMainFragment(mainFragment);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(game.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadMainFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game_frame_container, fragment);
        transaction.commit();
    }

    public void loadFragment(String fragmentName){
        switch(fragmentName){
            case "roomsFragment":
                loadFragment(roomsFragment);
                break;
            case "detailsFragment":
                loadFragment(detailsFragment);
                break;
            case "expansionsFragment":
                loadFragment(expansionsFragment);
                break;
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game_frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        menu.removeItem(R.id.search_button);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
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