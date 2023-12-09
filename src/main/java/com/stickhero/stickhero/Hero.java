/*
* THIS IS THE HERO CLASS WHICH IS USED BY THE OTHER CLASSES. THERE IS ONLY ONE INSTANCE OF THE HERO
* WHICH IS USED IN ALL THE OTHER CLASSES.
* */

package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero {
    private ImageView view;
    private boolean isUpsideDown;
    private static Hero instance;

    private Hero(){

    }
    public static Hero getInstance(){
        if(instance == null){
            instance = new Hero();
            return instance;
        }
        return instance;
    }

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
        this.isUpsideDown = false;

        return view;
    }

    public boolean isUpsideDown() {
        return isUpsideDown;
    }

    public void setUpsideDown(boolean upsideDown) {
        isUpsideDown = upsideDown;
    }
}
