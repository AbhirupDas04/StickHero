package com.stickhero.stickhero;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public abstract class Background_Handler {
    public Background background;

    public Pane returnBackground(){
        Pane pane = new Pane();
        pane.setBackground(this.background);
        return pane;
    }
}
