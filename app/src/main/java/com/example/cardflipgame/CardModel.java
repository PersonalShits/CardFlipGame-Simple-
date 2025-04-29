package com.example.cardflipgame;

public class CardModel {
    private int imageResId;
    private boolean isFlipped;
    private boolean isMatched;

    public CardModel(int imageResId) {
        this.imageResId = imageResId;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
