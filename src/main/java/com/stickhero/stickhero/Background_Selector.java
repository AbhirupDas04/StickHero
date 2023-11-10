package com.stickhero.stickhero;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Background_Selector {
    public Pane selectOne(){
        Image image = new Image(this.getClass().getResourceAsStream("Pic_4.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundimage);

        Pane pane = new HBox();
        pane.setBackground(background);
        return pane;
    }
}
