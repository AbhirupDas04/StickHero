package com.stickhero.stickhero;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HomeScreen extends BackgroundHandler {
    public HBox openHome(){
        Image image = new Image(getClass().getResourceAsStream("Pic_5.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));

        HBox pane = this.returnBackground();
        Button bt = new Button();
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:40;-fx-text-fill:white");
        bt.setShape(new Circle(150));
        bt.setMinSize(150,150);
        bt.setMaxSize(150,150);
        bt.setText("PLAY");
        Text text = new Text();

        //Setting font to the text
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //setting the position of the text
        text.setX(50);
        text.setY(130);

        //Setting the text to be added.
        text.setText("Hi how are you");
        pane.getChildren().addAll(bt,text);

        return pane;
    }
}
