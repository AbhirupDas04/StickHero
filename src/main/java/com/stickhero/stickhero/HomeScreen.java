package com.stickhero.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScreen extends BackgroundHandler {
    private Rectangle rectangle;
    private GameScreen game;
    public HomeScreen(Stage stage, GameScreen game){
        super(stage);
        this.game = game;
        super.setMusic(new IntroMusic());
    }
    public void openHome(){
        Image image = new Image(getClass().getResourceAsStream("Pic_10.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));

        Pane pane = this.returnBackground();
        Button bt = new Button();
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-font-size:30;-fx-text-fill:white");
        bt.setShape(new Circle(125));
        bt.setMinSize(125,125);
        bt.setMaxSize(125,125);
        bt.setText("PLAY");
        bt.setTranslateX(185);
        bt.setTranslateY(260);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text.setLayoutX(130);
        text.setLayoutY(100);
        text.setText("STICK");

        Text text2 = new Text();
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text2.setLayoutX(140);
        text2.setLayoutY(160);
        text2.setText("HERO");

        pane.getChildren().addAll(bt,text,text2);

        Pillar pillar = new Pillar();
        rectangle = pillar.generatePillar(100,120);
        rectangle.setLayoutX(200);
        rectangle.setLayoutY(530);

        pane.getChildren().add(rectangle);

        Image image2 = new Image(this.getClass().getResourceAsStream("IMG_8950.png"));
        ImageView view = new ImageView(image2);
        view.setPreserveRatio(true);
        view.setFitHeight(35);
        view.setFitWidth(35);
        view.setLayoutX(230);
        view.setLayoutY(495);
        pane.getChildren().add(view);

        Scene scene1 = new Scene(pane, 500, 650);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.startGame();
            }
        });
    }
}
