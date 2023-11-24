package com.stickhero.stickhero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.net.Authenticator;
import java.util.Random;

public class GameScreen extends BackgroundHandler {
    private Hero hero;
    private int gamespeed;
    private int n_cherries;
    private Image image;
    private HistoryStorage storage;
    private Score score;
    private int level;
    public GameScreen(Stage stage, HistoryStorage storage){
        super(stage);
        this.hero = new Hero();
        this.selectRandomImage();
        this.gamespeed = 1;
        this.score = new Score();
        this.n_cherries = 0;
        this.storage = storage;
        this.level = 0;
//        super.setMusic(new InGameMusic());
    }
    public void updateGameSpeed(int speed){
        this.gamespeed = speed;
    }
    private String selectRandomIndex(){
        Random random = new Random();
        int rand = random.nextInt(11) + 1;
        String num = Integer.toString(rand);
        return num;
    }
    private void selectRandomImage(){
        String randomImage = "Pic_" + selectRandomIndex() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(randomImage));

        BackgroundSize backgroundSize = new BackgroundSize(1000,650,false,false,true,true);
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(backgroundimage));
        this.image = image;
    }

    public void startGame(){
        Pane game_pane = this.returnBackground();
        Pillar pillar = new Pillar(100,160,0,490);
        Rectangle rectangle = pillar.getRectangle();

        Random rand = new Random();
        int rand_width = rand.nextInt(70) + 30;
        int rand_posX = rand.nextInt(370 - rand_width) + 130;
        pillar = new Pillar(rand_width,160,rand_posX,490);
        Rectangle rectangle2 = pillar.getRectangle();

        Rectangle rect3 = new Rectangle(10, 5);
        rect3.setFill(Color.RED);
        rect3.setLayoutX(rand_posX + rand_width / 2.0 - 5);
        rect3.setLayoutY(490);

        ImageView view = hero.generateStandingHero(35,35,60,455);

        Rectangle rectangle1 = new Rectangle(70, 40);
        rectangle1.setFill(Color.GRAY);
        rectangle1.setLayoutX(215);
        rectangle1.setLayoutY(50);
        rectangle1.setArcHeight(15);
        rectangle1.setArcWidth(15);
        rectangle1.setOpacity(0.4);

        Text text = new Text();
        text.setFont(Font.font("verdana", FontPosture.REGULAR, 36));
        text.setFill(Color.WHITE);
        text.setLayoutX(239);
        text.setLayoutY(84);
        text.setText("0");

        Reward cherry = new Reward();
        ImageView view1 = cherry.generateReward(30,30,467,35);

        Text text1 = new Text();
        text1.setFont(Font.font("verdana", FontPosture.REGULAR, 16));
        text1.setFill(Color.WHITE);
        text1.setLayoutX(450);
        text1.setLayoutY(56);
        text1.setText("0");

        Button bt = new Button("EXIT");
        bt.setMinSize(50,30);
        bt.setMaxSize(100,60);
        bt.setLayoutX(25);
        bt.setLayoutY(25);
        bt.setStyle("-fx-background-color:rgb(255, 0, 0);-fx-border-radius: 150;-fx-font-size:15;-fx-text-fill:white");

        game_pane.getChildren().addAll(rectangle,bt,rectangle2,rect3,rectangle1,view,view1,text,text1);

        Scene scene = new Scene(game_pane, 500, 650);
        super.getStage().setScene(scene);
        super.getStage().setResizable(false);
        super.getStage().show();

        EndScreen endScreen = new EndScreen(super.getStage(),new Hero(),this,this.image,storage);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bt.setVisible(false);
                text1.setVisible(false);
                text.setVisible(false);
                view1.setVisible(false);
                view.setVisible(false);
                rectangle1.setVisible(false);
                endScreen.endGame(game_pane,scene);
            }
        });
    }
}