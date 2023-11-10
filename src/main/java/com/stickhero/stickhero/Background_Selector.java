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

    public ArrayList<String> loadImages(){
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add("Pic_1.jpg");
        tempArray.add("Pic_2.jpg");
        tempArray.add("Pic_3.jpg");
        tempArray.add("Pic_4.jpg");
        tempArray.add("Pic_5.jpg");
        tempArray.add("Pic_6.jpg");
        tempArray.add("Pic_7.jpg");
        tempArray.add("Pic_8.jpg");
        tempArray.add("Pic_9.jpg");
        tempArray.add("Pic_10.jpg");
        tempArray.add("Pic_11.jpg");
        tempArray.add("Pic_12.jpg");

        return tempArray;
    }

    public String selectRandomImage(ArrayList<String> imagesArray){
        int rand = (int)(Math.random() * imagesArray.size());
        return imagesArray.get(rand);
    }
    public HBox selectOne(){
        setImagesArray(loadImages());

//        Image image = new Image(this.getClass().getResourceAsStream("Pic_3.jpg"));

        Image image = new Image(this.getClass().getResourceAsStream(selectRandomImage(imagesArray)));

        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundimage);

        HBox hbox = new HBox();
        hbox.setBackground(background);
        return hbox;
    }
}