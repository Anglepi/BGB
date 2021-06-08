package com.boardgamesbrotherhood.bgb.Models;

import java.util.ArrayList;

public class UserAccount {
    private String uID;
    private String firstName;
    private String lastName;
    private ArrayList<String> userFavGames;

    public UserAccount(String uID, String firstName, String lastName, ArrayList<String> userFavGames) {
        this.uID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userFavGames = userFavGames;

    }

    public UserAccount(UserAccount original){
        this.firstName = original.getFirstName();
        this.lastName = original.getLastName();
        this.userFavGames = original.getUserFavGames();
    }

    public UserAccount() {
        this.uID = null;
        this.firstName = "";
        this.lastName = "";
        this.userFavGames = new ArrayList<>();
    }

    public String getuID() {
        return uID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getUserFavGames() {
        return userFavGames;
    }

    public void addOrRemoveLikedGame(String gameName){
        if(userFavGames.contains(gameName)){
            userFavGames.remove(gameName);
        } else {
            userFavGames.add(gameName);
        }

        UserSession.UpdateUserData();
    }

    public UserAccount toDatabase() {
        UserAccount toSave = new UserAccount(this);
        return toSave;
    }

    public boolean likesGame(String game) {
        return userFavGames.contains(game);
    }
}
