package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Reward {
    private int x_pos;
    private int y_pos;
    private int height;
    private int width;

    public ImageView generateReward(int height, int width, int x_pos, int y_pos){
        Image image3 = new Image(this.getClass().getResourceAsStream("cherry.png"));
        ImageView view1 = new ImageView(image3);
        view1.setPreserveRatio(true);
        view1.setFitHeight(height);
        view1.setFitWidth(width);
        view1.setLayoutX(x_pos);
        view1.setLayoutY(y_pos);

        return view1;
    }

    public int getX_pos(){
        return x_pos;
    }
    public int getY_pos(){
        return y_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }


}
