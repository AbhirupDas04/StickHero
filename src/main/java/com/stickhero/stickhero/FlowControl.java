package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.IOException;

public class FlowControl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        BackgroundHandler game = new GameScreen();
//        HBox game_pane = game.returnBackground();
//        Image image = new Image(this.getClass().getResourceAsStream("IMG_8951.png"));
//        pane.getChildren().add(new ImageView(image));

//        Scene scene = new Scene(game_pane, 500, 650);
//        primaryStage.setScene(scene);

        HomeScreen home = new HomeScreen();
        HBox hbox = home.openHome();
        Scene scene = new Scene(hbox, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
