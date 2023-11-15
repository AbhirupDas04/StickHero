package com.stickhero.stickhero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class FlowControl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BackgroundHandler game = new GameScreen();
        Pane game_pane = game.returnBackground();

        Pillar pillar = new Pillar();
        Rectangle rectangle = pillar.generatePillar(100,160);
        rectangle.setLayoutX(0);
        rectangle.setLayoutY(490);

        game_pane.getChildren().add(rectangle);

        Random rand = new Random();
        int rand_width = rand.nextInt(70) + 30;
        Rectangle rectangle2 = pillar.generatePillar(rand_width,160);
        int rand_posX = rand.nextInt(370 - rand_width) + 130;
        rectangle2.setLayoutX(rand_posX);
        rectangle2.setLayoutY(490);

        game_pane.getChildren().add(rectangle2);

        Rectangle rect3 = new Rectangle(10, 5);
        rect3.setFill(Color.RED);
        rect3.setLayoutX(rand_posX + rand_width / 2.0 - 5);
        rect3.setLayoutY(490);
        game_pane.getChildren().add(rect3);

        Image image2 = new Image(this.getClass().getResourceAsStream("IMG_8950.png"));
        ImageView view = new ImageView(image2);
        view.setPreserveRatio(true);
        view.setFitHeight(35);
        view.setFitWidth(35);
        view.setLayoutX(60);
        view.setLayoutY(455);
        game_pane.getChildren().add(view);

        Scene scene = new Scene(game_pane, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        HomeScreen home = new HomeScreen();
        Pane pane = home.openHome();
        Scene scene1 = new Scene(pane, 500, 650);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
