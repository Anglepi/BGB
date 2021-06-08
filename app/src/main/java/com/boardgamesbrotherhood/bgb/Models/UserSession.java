package com.boardgamesbrotherhood.bgb.Models;

import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserSession {
    public static boolean SessionEstablished;
    public static FirebaseUser CurrentUser;
    public static String FirstName;
    public static String LastName;
    public static ArrayList<Game> UserFavGames;

    public static void Login(FirebaseUser u){
        SessionEstablished = true;
        CurrentUser = u;

        UserSession.LoadUserData();
    }

    public static void LoadUserData(){
        //TODO load user data from API
        FirstName = "";
        LastName = "";
        UserFavGames = new ArrayList<>();
    }

    public static void Logout(){
        SessionEstablished = false;
        CurrentUser = null;
        FirstName = null;
        LastName = null;
        UserFavGames = null;
    }
}
