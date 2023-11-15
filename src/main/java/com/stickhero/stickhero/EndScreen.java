package com.stickhero.stickhero;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EndScreen extends BackgroundHandler {
    private Hero hero;
    private GameScreen game;
    private Image image;
    public EndScreen(Stage stage, Hero hero, GameScreen game, Image image){
        super(stage);
        this.hero = hero;
        this.game = game;
        this.image = image;
    }
    public void endGame(){
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(this.image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));

        Pane pane = this.returnBackground();
        Scene scene1 = new Scene(pane, 500, 650);
        super.getStage().setScene(scene1);
        super.getStage().show();
    }
}
