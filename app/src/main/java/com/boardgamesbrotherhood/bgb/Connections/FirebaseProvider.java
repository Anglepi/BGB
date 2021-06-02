package com.boardgamesbrotherhood.bgb.Connections;

import androidx.annotation.NonNull;

import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.OnDataLoaded;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseProvider {
    private static final FirebaseDatabase fdb = FirebaseDatabase.getInstance("https://boardgames-brotherhood-default-rtdb.europe-west1.firebasedatabase.app/");

    public static void loadGames(OnDataLoaded odl){
        fdb.getReference().child("games").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot tendria los datos de "games"
                List<Game> games = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    games.add(new Game(ds.child("title").getValue().toString(), ds.child("thumbnail").getValue().toString()));
                }
                odl.onTaskComplete(games);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
