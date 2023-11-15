package com.stickhero.stickhero;

import javafx.scene.image.Image;
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

    }
}
