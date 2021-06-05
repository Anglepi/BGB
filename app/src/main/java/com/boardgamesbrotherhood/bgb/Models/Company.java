package com.boardgamesbrotherhood.bgb.Models;

import android.content.Context;

import com.boardgamesbrotherhood.bgb.CardDisplayable;

public class Company implements CardDisplayable {
    String name;
    String image;

    public Company(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String getCardTitle() {
        return getName();
    }

    @Override
    public String getCardThumbnail() {
        return getImage();
    }

    @Override
    public void openCard(Context context){

    }
}
