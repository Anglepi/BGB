package com.boardgamesbrotherhood.bgb.Connections;

import androidx.annotation.NonNull;

import com.boardgamesbrotherhood.bgb.Models.Category;
import com.boardgamesbrotherhood.bgb.Models.Company;
import com.boardgamesbrotherhood.bgb.Models.Establishment;
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
                    games.add(new Game(ds.child("title").getValue().toString(), ds.child("thumbnail").getValue().toString(), ds.child("description").getValue().toString()));
                }
                odl.onTaskComplete(games);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void loadCategories(OnDataLoaded odl){
        fdb.getReference().child("categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    categories.add(new Category(ds.child("title").getValue().toString(), ds.child("thumbnail").getValue().toString()));
                }
                odl.onTaskComplete(categories);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void loadEstablishments(OnDataLoaded odl){
        fdb.getReference().child("establishments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Establishment> establishments = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    establishments.add(new Establishment(ds.child("name").getValue().toString(), ds.child("thumbnail").getValue().toString(), ds.child("address").getValue().toString()));
                }
                odl.onTaskComplete(establishments);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static void loadCompanies(OnDataLoaded odl){
        fdb.getReference().child("companies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Company> companies = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    companies.add(new Company(ds.child("name").getValue().toString(), ds.child("thumbnail").getValue().toString()));
                }
                odl.onTaskComplete(companies);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
