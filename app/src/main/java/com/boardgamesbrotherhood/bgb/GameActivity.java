package com.boardgamesbrotherhood.bgb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.boardgamesbrotherhood.bgb.Models.Game;

public class GameActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = getIntent().getExtras().getParcelable("game");
        setContentView(R.layout.activity_game);
        System.out.println("Abierto juego "+game.getTitle());
    }
}