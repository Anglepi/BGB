package com.boardgamesbrotherhood.bgb.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.boardgamesbrotherhood.bgb.CardDisplayable;
import com.boardgamesbrotherhood.bgb.MainActivity;

public class Game implements CardDisplayable, Parcelable {
    private String title;
    private String description;
    private String thumbnail;

    public Game(String title, String thumbnail, String description){
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    protected Game(Parcel in) {
        //El orden debe ser el mismo que en el constructor
        title = in.readString();
        thumbnail = in.readString();
        description = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
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

    @Override
    public void openCard(){
        MainActivity.openGame(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeString(getThumbnail());
        dest.writeString(getDescription());
    }
}
