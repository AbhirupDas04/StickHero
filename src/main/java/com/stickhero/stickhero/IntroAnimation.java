package com.stickhero.stickhero;

import javafx.stage.Stage;

public class IntroAnimation extends BackgroundHandler {
    public IntroAnimation(Stage stage){
        super(stage);
        this.setMusic(new IntroMusic());
    }
    public void startIntro(){}
}
