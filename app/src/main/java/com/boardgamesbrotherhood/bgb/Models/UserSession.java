package com.boardgamesbrotherhood.bgb.Models;

import com.boardgamesbrotherhood.bgb.Activities.MainActivity;
import com.boardgamesbrotherhood.bgb.Connections.FirebaseProvider;
import com.boardgamesbrotherhood.bgb.OnDataLoaded;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserSession {
    public static boolean SessionEstablished = false;
    public static FirebaseUser CurrentUser;
    public static UserAccount user = new UserAccount();


    public static void Login(FirebaseUser u){
        SessionEstablished = true;
        CurrentUser = u;

        UserSession.LoadUserData();
    }

    public static void LoadUserData(){
        FirebaseProvider.loadUsers(new OnDataLoaded() {
            @Override
            public void onTaskComplete(List<?> users) {
                boolean existentUser = false;
                for(int i=0 ; i<users.size() && !existentUser ; i++){
                    UserAccount ua = (UserAccount) users.get(i);
                    if(ua.getuID().equals(CurrentUser.getUid())){
                        user = ua;
                        existentUser = true;
                    }
                }

                if(!existentUser){
                    createUser();
                    user = new UserAccount();
                }

            }
        });
    }

    public static void Logout(){
        SessionEstablished = false;
        CurrentUser = null;
        user = null;
    }

    private static void createUser(){
        FirebaseProvider.createNewUser(CurrentUser.getUid());
    }

    public static void UpdateUserData() {
        FirebaseProvider.updateExistingUser(user);
    }
}
