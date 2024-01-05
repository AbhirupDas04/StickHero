/*
 * THIS CLASS IS USED BY GAMESCREEN CLASS GENERATE PILLARS IN THE GAME.
 * */



package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Pillar {
    private int height;
    private int width;
    private int x_pos;
    private int y_pos;
    private Rectangle rectangle;
    private boolean prev_used;

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setRectangle(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public int getHeight(){
        return this.height;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setPrev_used(boolean prev_used) {
        this.prev_used = prev_used;
    }

    private static Map<String, Pillar> hashmap = new HashMap<String,Pillar>();

    public Pillar(int width,int height, int x_pos, int y_pos){
        Rectangle rectangle = new Rectangle(width,height);
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.width = width;
        rectangle.setVisible(true);
        rectangle.setLayoutX(x_pos);
        rectangle.setLayoutY(y_pos);
        this.rectangle = rectangle;
        this.prev_used = false;
    }

    public static Pillar getInstance(int width, int height, int x_pos, int y_pos){
        String key = width + " " + x_pos;
        if(!hashmap.containsKey(key)){
            hashmap.put(key, new Pillar( width, height, x_pos, y_pos));
        }
        else{
            hashmap.get(key).setPrev_used(true);
        }

        hashmap.get(key).getRectangle().setVisible(true);
        hashmap.get(key).getRectangle().setLayoutX(x_pos);
        hashmap.get(key).getRectangle().setLayoutY(y_pos);
        hashmap.get(key).getRectangle().setTranslateX(0);

        return hashmap.get(key);
    }

    public static Pillar getInstance(int width, int height, int x_pos, int y_pos, double first_width, double first_xpos){
        if((int)first_width == width && (int)first_xpos == x_pos ){
            return new Pillar(width, height, x_pos, y_pos);
        }
        String key = width + " " + x_pos;
        if(!hashmap.containsKey(key)){
            hashmap.put(key, new Pillar( width, height, x_pos, y_pos));
        }
        else{
            hashmap.get(key).setPrev_used(true);
        }

        hashmap.get(key).getRectangle().setVisible(true);
        hashmap.get(key).getRectangle().setLayoutX(x_pos);
        hashmap.get(key).getRectangle().setLayoutY(y_pos);

        return hashmap.get(key);
    }

    public boolean isPrev_used() {
        return prev_used;
    }
}
