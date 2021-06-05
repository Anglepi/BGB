package com.boardgamesbrotherhood.bgb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.fragments.GameFragments.*;

public class GameActivity extends AppCompatActivity {
    public static Game game;
    private Fragment mainFragment, roomsFragment, detailsFragment, expansionsFragment;
    private static GameActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getIntent().getExtras().getParcelable("game");
        setContentView(R.layout.activity_game);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(game.getTitle());

        mainFragment = new MainGameFragment();
        roomsFragment = new RoomsGameFragment();
        detailsFragment = new DetailsGameFragment();
        expansionsFragment = new ExpansionsGameFragment();

        loadMainFragment(mainFragment);

        instance = this;
    }

    private void loadMainFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.game_frame_container, fragment);
        transaction.commit();
    }

    public static GameActivity getInstance(){
        return instance;
    }

    public static void switchToFragment(String fragmentName){
        instance.loadFragment(fragmentName);
    }

    private void loadFragment(String fragmentName){
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
}