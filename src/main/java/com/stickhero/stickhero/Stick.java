/*
 * THIS CLASS INSTANCE IS USED TO CREATE A STICK IN THE GAMESCREEN CLASS.
 * */

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
        Rectangle rectangle = new Rectangle(width,0);
        rectangle.setLayoutX(x_pos);
        rectangle.setLayoutY(y_pos);
        this.rectangle = rectangle;
        this.speed = 1;
        this.length = 0;
        this.height = 0;
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

    public int getLength() {
        return length;
    }

    public float getSpeed() {
        return speed;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public Rectangle generateStick(){
        return this.rectangle;
    }
    public void extendLength(){
        this.length += 4;
        this.rectangle.setLayoutY(this.y_pos - 2);
        this.rectangle.setHeight(this.height + 2);
        this.height+=4;
        this.y_pos-=4;
    }
    public void resetLength(){}
    public void changeStickAngle(){}
}
