package com.boardgamesbrotherhood.bgb;

import com.boardgamesbrotherhood.bgb.Models.Game;

import java.util.List;

public interface OnGamesLoaded {
    public void onTaskComplete(List<Game> games);
}
