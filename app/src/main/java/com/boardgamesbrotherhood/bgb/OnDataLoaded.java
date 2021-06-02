package com.boardgamesbrotherhood.bgb;

import com.boardgamesbrotherhood.bgb.Models.Game;

import java.util.List;

public interface OnDataLoaded {
    public void onTaskComplete(List<?> games);
}
