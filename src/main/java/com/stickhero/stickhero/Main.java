package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Background_Selector selector = new Background_Selector();
        Pane pane = selector.selectOne();
        Image image = new Image(this.getClass().getResourceAsStream("IMG_8951.png"));
        pane.getChildren().add(new ImageView(image));

        Scene scene = new Scene(pane, 1000, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
