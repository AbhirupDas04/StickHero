package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Background_Selector {
    public HBox selectOne(){
        Image image = new Image(this.getClass().getResourceAsStream("Pic_2.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundimage);

        HBox hbox = new HBox();
        hbox.setBackground(background);
        return hbox;
    }
}
