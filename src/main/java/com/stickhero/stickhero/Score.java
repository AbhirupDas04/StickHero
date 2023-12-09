package com.stickhero.stickhero;

import javafx.scene.image.Image;

public class Score {
    private int score;
    private Image image;

    public void setScore(int score){
        this.score = score;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public int getScore() {
        return score;
    }

    public Image getImage() {
        return image;
    }

    public Image returnScorePic(){return null;}
}
