package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Background_Selector {
    public ArrayList<String> imagesArray = new ArrayList<>();

    public void setImagesArray(ArrayList<String> imagesArray){
        this.imagesArray = imagesArray;
    }
    public String selectRandomImage(){
        int rand = (int)(Math.random() * 12) + 1;
        String num = Integer.toString(rand);
        return num;
    }
    public HBox selectOne(){
        String randomImage = "Pic_" + selectRandomImage() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(randomImage));

        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundimage);

        HBox hbox = new HBox();
        hbox.setBackground(background);
        return hbox;
    }
}