package com.boardgamesbrotherhood.bgb.Models;

public class Game {
    private String title;
    private String company;
    private String thumbnail;

    public Game(String title, String thumbnail){
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle(){
        return title;
    }

    public String getCompany(){
        return company;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
