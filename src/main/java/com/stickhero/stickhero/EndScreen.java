package com.stickhero.stickhero;

public class EndScreen extends BackgroundHandler {
    private Hero hero;
    private GameScreen game;
    public EndScreen(Hero hero, GameScreen game){
        this.hero = hero;
        this.game = game;
    }
    public void endGame(){}
}
