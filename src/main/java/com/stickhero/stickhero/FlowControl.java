package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
        Pane pane = home.openHome();
        Scene scene = new Scene(pane, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
