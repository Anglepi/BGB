package com.boardgamesbrotherhood.bgb.Models;

import com.boardgamesbrotherhood.bgb.CardDisplayable;

public class Game implements CardDisplayable {
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

    @Override
    public String getCardTitle() {
        return getTitle();
    }

    @Override
    public String getCardThumbnail() {
        return getThumbnail();
    }
}
