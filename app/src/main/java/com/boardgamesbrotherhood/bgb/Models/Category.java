package com.boardgamesbrotherhood.bgb.Models;

import com.boardgamesbrotherhood.bgb.CardDisplayable;

public class Category implements CardDisplayable {
    private String name;
    private String thumbnail;

    public Category(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public String getCardTitle() {
        return getName();
    }

    @Override
    public String getCardThumbnail() {
        return getThumbnail();
    }

    @Override
    public void openCard(){

    }
}
