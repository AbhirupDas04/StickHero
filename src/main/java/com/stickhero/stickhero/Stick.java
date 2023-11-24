package com.stickhero.stickhero;

import javafx.scene.shape.Rectangle;

public class Stick {
    private int x_pos;
    private int length;
    private float speed;
    private Rectangle rectangle;
    private int y_pos;
    private int height;

    public Stick(int width, int x_pos, int y_pos){
        Rectangle rectangle = new Rectangle(width,10);
        rectangle.setLayoutX(x_pos);
        rectangle.setLayoutY(y_pos);
        this.rectangle = rectangle;
        this.speed = 1;
        this.length = 10;
        this.height = 10;
        this.y_pos = y_pos;
        this.x_pos = x_pos;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle generateStick(){
        return this.rectangle;
    }
    public void extendLength(){
        this.length += 1;
        this.rectangle.setLayoutY(this.y_pos - 1);
        this.rectangle.setHeight(this.height + 1);
        this.height+=1;
        this.y_pos-=1;
    }
    public void resetLength(){}
    public void changeStickAngle(){}
}
