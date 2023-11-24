package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero {
    private float speed;
    private int n_rewards;
    private Score score;
    private int level;
    private int cherriesCollected;
    private ImageView view;

    public ImageView getView() {
        return view;
    }

    public ImageView generateStandingHero(int height, int width, int x_pos, int y_pos){
        Image image2 = new Image(this.getClass().getResourceAsStream("Standing_Hero.png"));
        ImageView view = new ImageView(image2);
        this.view = view;
        view.setPreserveRatio(true);
        view.setFitHeight(height);
        view.setFitWidth(width);
        view.setLayoutX(x_pos);
        view.setLayoutY(y_pos);

        return view;
    }
    public void moveToSide(){}
    public void moveUp(){}
    public void moveDown(){}
    public void collectCherry(){}
    public void updateLevelCount(int level){}
}
