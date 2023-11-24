package com.stickhero.stickhero;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class BackgroundHandler {
    private Background background;
    private Stage stage;
    private Pane pane;
    private MusicHandler music;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Pane getPane() {
        return pane;
    }

    public void setMusic(MusicHandler music) {
        this.music = music;
    }

    public BackgroundHandler(Stage stage){
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Pane returnBackground(){
        Pane pane = new Pane();
        pane.setBackground(this.background);
        return pane;
    }
}
