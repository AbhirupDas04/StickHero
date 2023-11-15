package com.stickhero.stickhero;

import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class BackgroundHandler {
    private Background background;
    private Stage stage;

    public void setBackground(Background background) {
        this.background = background;
    }

    public Pane returnBackground(){
        Pane pane = new Pane();
        pane.setBackground(this.background);
        return pane;
    }
}