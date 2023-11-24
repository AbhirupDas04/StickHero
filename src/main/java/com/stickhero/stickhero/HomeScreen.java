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
    private Pillar pillar;
    private GameScreen game;
    private HistoryStorage storage;
    public HomeScreen(Stage stage, GameScreen game, HistoryStorage storage){
        super(stage);
        this.game = game;
        super.setMusic(new IntroMusic());
        this.storage = storage;

        Image image = new Image(getClass().getResourceAsStream("Pic_10.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));
    }
    public void openHome() throws InterruptedException{
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

        Pillar pillar = new Pillar(100,120,200,530);
        Rectangle rectangle = pillar.getRectangle();

        Hero hero = new Hero();
        ImageView view = hero.generateStandingHero(35,35,230,495);

        pane.getChildren().addAll(bt,text,text2,rectangle,view);

        Scene scene1 = new Scene(pane, 500, 650);
        super.getStage().setScene(scene1);
        super.getStage().setResizable(false);
        super.getStage().show();

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    game.startGame();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
