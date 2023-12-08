package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FlowControl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HistoryStorage storage = new HistoryStorage();
        GameScreen game = new GameScreen(primaryStage,storage);
        HomeScreen home = new HomeScreen(primaryStage,game, storage);

        home.openHome();
    }
}
