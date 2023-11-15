package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Pillar {
    private int height;
    private int width;
    public Rectangle generatePillar(int width,int height){
        Rectangle rectangle = new Rectangle(width,height);
        return rectangle;
    }
}
