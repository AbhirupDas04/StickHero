package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Pillar {
    private int height;
    private int width;
    private int x_pos;
    private int y_pos;
    private Rectangle rectangle;
    public Pillar(int width,int height, int x_pos, int y_pos){
        Rectangle rectangle = new Rectangle(width,height);
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        rectangle.setLayoutX(x_pos);
        rectangle.setLayoutY(y_pos);
        this.rectangle = rectangle;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
