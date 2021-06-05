package com.boardgamesbrotherhood.bgb.Models;

import com.boardgamesbrotherhood.bgb.CardDisplayable;

public class Establishment implements CardDisplayable {
    public String name;
    public String image;
    public String address;

    public Establishment(String name, String image, String address) {
        this.name = name;
        this.image = image;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getCardTitle() {
        return getName()+"\n"+getAddress();
    }

    @Override
    public String getCardThumbnail() {
        return getImage();
    }

    @Override
    public void openCard(){

    }
}
