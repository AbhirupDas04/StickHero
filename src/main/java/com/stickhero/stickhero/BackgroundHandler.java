package com.stickhero.stickhero;

import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class BackgroundHandler {
    private Background background;

    public void setBackground(Background background) {
        this.background = background;
    }

    public HBox returnBackground(){
        HBox hbox = new HBox();
        hbox.setBackground(this.background);
        return hbox;
    }
}
