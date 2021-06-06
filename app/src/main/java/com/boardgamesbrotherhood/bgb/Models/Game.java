package com.boardgamesbrotherhood.bgb.Models;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.boardgamesbrotherhood.bgb.CardDisplayable;
import com.boardgamesbrotherhood.bgb.ExtendedCardDisplayable;
import com.boardgamesbrotherhood.bgb.GameActivity;
import com.boardgamesbrotherhood.bgb.MainActivity;

import java.util.ArrayList;

public class Game implements ExtendedCardDisplayable, Parcelable {
    private String title;
    private String description;
    private String thumbnail;
    private ArrayList<String> categories;

    public Game(String title, String thumbnail, String description, ArrayList<String> categories){
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.categories = categories;
    }

    protected Game(Parcel in) {
        //El orden debe ser el mismo que en el constructor
        title = in.readString();
        thumbnail = in.readString();
        description = in.readString();
        in.readList(categories, String.class.getClassLoader());
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

    public ArrayList<String> getCategories() { return categories; }

    @Override
    public String getCardTitle() {
        return getTitle();
    }

    @Override
    public String getCardThumbnail() {
        return getThumbnail();
    }

    @Override
    public String getCardDescription() { return getDescription(); }

    @Override
    public void openCard(Context context){
        Intent i = new Intent(context, GameActivity.class);
        i.putExtra("game",this);
        //i.addFlags(Intent.)
        context.startActivity(i);
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
