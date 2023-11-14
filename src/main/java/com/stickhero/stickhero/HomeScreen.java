package com.stickhero.stickhero;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

public class HomeScreen extends BackgroundHandler {
    public HBox openHome(){
        Image image = new Image(getClass().getResourceAsStream("Pic_5.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));

        HBox pane = this.returnBackground();
        Button bt = new Button();
        bt.setShape(new Circle(200));
        bt.setMinSize(200,200);
        bt.setMaxSize(200,200);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(bt);

        return pane;
    }
}
