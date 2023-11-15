package com.stickhero.stickhero;

import javafx.stage.Stage;

public class EndScreen extends BackgroundHandler {
    private Hero hero;
    private GameScreen game;
    public EndScreen(Stage stage, Hero hero, GameScreen game){
        super(stage);
        this.hero = hero;
        this.game = game;
    }
    public void endGame(){}
}
