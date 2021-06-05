package com.boardgamesbrotherhood.bgb;

import android.content.Context;

public interface CardDisplayable {
    public String getCardTitle();
    public String getCardThumbnail();
    public void openCard(Context current_context);
}
